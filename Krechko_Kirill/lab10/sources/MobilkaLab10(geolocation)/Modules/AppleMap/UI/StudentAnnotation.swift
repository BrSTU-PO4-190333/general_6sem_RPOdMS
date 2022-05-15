//
//  StudentAnnotation.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import MapKit

// MARK: - Constants

private enum Constants {
    static let dateFormat = "yyyy-MM-dd"
}

// MARK: - StudentAnnotation

class StudentAnnotation: MKPointAnnotation {

    var id: UUID?
    var student: Student
    var willCome = false

    init(student: Student) {
        self.student = student
        super.init()
        setupAnnotation()
    }

    private func setupAnnotation() {
        let dateFormat = DateFormatter()
        dateFormat.dateFormat = Constants.dateFormat

        self.id = student.id
        self.title = "\(student.firstname) \(student.lastname)"
        self.subtitle = dateFormat.string(from: student.dateOfBirth)
        self.coordinate = student.coordinate
    }
}
