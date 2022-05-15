//
//  AppleMapViewModel.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import Foundation
import MapKit

// MARK: - AppleMapViewModelInterface

protocol AppleMapViewModelInterface {

    var tableData: [Student] { get }

    func viewDidLoad()

    func getStudentImage(id: UUID) -> UIImage
    func showStudentInfo(id: UUID)
    func setupCountOfStudentsText(mapView: MKMapView, circlesCenter: CLLocationCoordinate2D) -> String
    func setStudentsWillTheyCome(annotations: [MKAnnotation], center: CLLocationCoordinate2D)
}

// MARK: - AppleMapViewModel

class AppleMapViewModel {

    weak var output: AppleMapOutput?
    private let usecase: StudentsUsecase

    var tableData: [Student] = []

    init(output: AppleMapOutput?, usecase: StudentsUsecase) {
        self.output = output
        self.usecase = usecase
    }

    private func getTableData() {
        tableData = usecase.getAllStudents()
    }

    private func getCountOfStudents(mapView: MKMapView, circlesCenter: CLLocationCoordinate2D) -> StudentCounter {
        var studentCounter = StudentCounter(smallRadius: -1, mediumRadius: .zero, largeRadius: .zero)
            // -1 becase venue is also counted :)

        mapView.annotations.forEach { annotation in
            guard let rangeType = Ranges.lowRange.getRange(
                studentCoordinate: circlesCenter.distance(from: annotation.coordinate)
            ) else { return }

            switch rangeType {
            case .lowRange:
                studentCounter.smallRadius += 1

            case .mediumRange:
                studentCounter.mediumRadius += 1

            case .highRange:
                studentCounter.largeRadius += 1
            }
        }
        return studentCounter
    }

    private func willStudentCome(studentCoordinate: CLLocationDistance) -> Bool {
        guard let rangeType = Ranges.lowRange.getRange(studentCoordinate: studentCoordinate)
        else { return false }

        return rangeType.willStudentCome
    }
}

// MARK: - AppleMapViewModelInterface

extension AppleMapViewModel: AppleMapViewModelInterface {

    func viewDidLoad() {
        getTableData()
    }

    func showStudentInfo(id: UUID) {
        guard let student = tableData.first(where: { id == $0.id }) else { return }
        output?.showStudentInfo(student: student)
    }

    func setupCountOfStudentsText(mapView: MKMapView, circlesCenter: CLLocationCoordinate2D) -> String {
        let counter = getCountOfStudents(mapView: mapView, circlesCenter: circlesCenter)
        return
            """
            \(counter.smallRadiusTitle) \(counter.smallRadius)
            \(counter.mediumRadiusTitle) \(counter.mediumRadius)
            \(counter.largeRadiusTitle) \(counter.largeRadius)
            """
    }

    func setStudentsWillTheyCome(annotations: [MKAnnotation], center: CLLocationCoordinate2D) {
        annotations.forEach { annotation in
            guard let annotation = annotation as? StudentAnnotation else { return }
            annotation.willCome = willStudentCome(studentCoordinate: center.distance(from: annotation.coordinate))
        }
    }

    func getStudentImage(id: UUID) -> UIImage {
        return tableData.first(where: { id == $0.id })?.image ?? .init()
    }
}

// MARK: - AppleMapInput

extension AppleMapViewModel: AppleMapInput { }
