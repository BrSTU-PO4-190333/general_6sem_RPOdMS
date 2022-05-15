//
//  StudentInfoViewModel.swift
//  Lesson24
//
//  Created by Harbros 109 on 3.01.22.
//

import MapKit
import UIKit

private enum Constants {
    static let dateFormat = "yyyy-MM-dd"
}

// MARK: - StudentInfoViewModelInterface

protocol StudentInfoViewModelInterface {

    var tableData: [String] { get }
    var image: UIImage { get }

    func viewDidLoad()
}

// MARK: - StudentInfoViewModel

class StudentInfoViewModel {

    weak var view: StudentInfoViewControllerInterface?
    weak var output: StudentInfoOutput?

    var tableData: [String] = []

    var image = UIImage()

    private let usecase: StudentsUsecase

    init(output: StudentInfoOutput, usecase: StudentsUsecase) {
        self.output = output
        self.usecase = usecase
    }
}

// MARK: - StudentViewModelInterface

extension StudentInfoViewModel: StudentInfoViewModelInterface {
    func viewDidLoad() { }
}

extension StudentInfoViewModel: StudentInfoInput {
    func showStudentInfo(student: Student) {

        defer {
            view?.reloadData()
        }

        let dateFormat = DateFormatter()
        dateFormat.dateFormat = Constants.dateFormat
        let dateOfBirth = dateFormat.string(from: student.dateOfBirth)

        self.tableData = [
            "Fullname: \(student.firstname) \(student.lastname)",
            "Date of birth: \(dateOfBirth)",
            "Gender: \(student.gender.rawValue)"
        ]

        usecase.getAddressFromLatLon(
            latitude: student.coordinate.latitude,
            longitude: student.coordinate.longitude,
            completionHandler: { [weak self] address in
                self?.tableData.append("Address: \(address)")
                self?.view?.reloadData()
            }
        )

        image = student.image
    }
}
