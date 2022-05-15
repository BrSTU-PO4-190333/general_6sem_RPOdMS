//
//  StudentUsecase.swift
//  Lesson24
//
//  Created by Harbros 109 on 2.01.22.
//

import MapKit
import UIKit

// MARK: - Constants

private enum Constants {
    static let countOfStudents = Int.random(in: 10...30)

    static let maxAge = 35
    static let minAge = 17

    static let brestLatitude = 52.097_5
    static let brestLongitude = 23.687_7

    static let randomRange = (-0.1...0.1)
}

// MARK: - StudentsUsecase

protocol StudentsUsecase {
    func getAllStudents() -> [Student]
    func getAddressFromLatLon(latitude: Double, longitude: Double, completionHandler: @escaping (String) -> Void)
}

// MARK: - StudentsUsecaseImpl

class StudentsUsecaseImpl {
    init () { }
}

// MARK: - StudentsUsecase
extension StudentsUsecaseImpl: StudentsUsecase {

    func getAllStudents() -> [Student] {

        return (1...Constants.countOfStudents).map { _ in
            return appendStudent()
        }
    }

    private func appendStudent() -> Student {

        let gender = Gender.allCases.randomElement() ?? .male

        var firstname = String()
        var image = UIImage()

        if gender == .male {
            firstname = StudentsDataBase().studentsMaleNameList.randomElement() ?? .init()
            image = UIImage(named: StudentsDataBase().studentsMaleImagesList.randomElement() ?? "") ?? .init()
        } else {
            firstname = StudentsDataBase().studentsFemaleNameList.randomElement() ?? .init()
            image = UIImage(named: StudentsDataBase().studentsFemaleImagesList.randomElement() ?? "") ?? .init()
        }

        return Student(
            firstname: firstname,
            lastname: StudentsDataBase().studentsSurnameList.randomElement() ?? .init(),
            gender: gender,
            dateOfBirth: createRandomDayOfBirth(),
            image: image,
            coordinate: createRandomCoordinate()
        )
    }

    private func createRandomDayOfBirth() -> Date {
        let maxDate = Calendar.current.date(byAdding: .year, value: -Constants.maxAge, to: Date()) ?? .init()
        let minDate = Calendar.current.date(byAdding: .year, value: -Constants.minAge, to: Date()) ?? .init()

        return Date(
            timeIntervalSinceNow: TimeInterval.random(in: maxDate.timeIntervalSinceNow...minDate.timeIntervalSinceNow)
        )
    }

    private func createRandomCoordinate() -> CLLocationCoordinate2D {
        return CLLocationCoordinate2D(
            latitude: Constants.brestLatitude + Double.random(in: Constants.randomRange),
            longitude: Constants.brestLongitude + Double.random(in: Constants.randomRange)
        )
    }

    func getAddressFromLatLon(latitude: Double, longitude: Double, completionHandler: @escaping (String) -> Void) {
        let location = CLLocation(latitude: latitude, longitude: longitude)

        CLGeocoder().reverseGeocodeLocation(location, completionHandler: { placemarks, error in

            if error != nil {
                print(error?.localizedDescription ?? .init())
            }

            guard let placemarks = placemarks, !placemarks.isEmpty, let placemark = placemarks.first else { return }
            let addressString = [
                placemark.subLocality,
                placemark.thoroughfare,
                placemark.locality,
                placemark.country,
                placemark.postalCode
            ].compactMap { $0 }.joined(separator: ", ")

            completionHandler(addressString)
        })
    }
}
