//
//  Student.swift
//  Lesson24
//
//  Created by Harbros 109 on 2.01.22.
//

import MapKit
import UIKit

// MARK: - Student

struct Student {
    let firstname: String
    let lastname: String
    let gender: Gender
    let dateOfBirth: Date
    let image: UIImage
    let coordinate: CLLocationCoordinate2D
    let id = UUID()
}
