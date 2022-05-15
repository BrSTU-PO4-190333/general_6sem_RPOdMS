//
//  ShowStudentButton.swift
//  Lesson24
//
//  Created by Harbros 109 on 22.01.22.
//

import MapKit
import UIKit

// MARK: - Constants

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"

    static let showStudentsButtonTitle = "Show Students"
    static let showStudentsButtonBackgroundColor = UIColor.blue

    static let brestLatitude = 52.097_5
    static let brestLongitude = 23.687_7
    static let brestCoordinate2D = CLLocationCoordinate2D(latitude: brestLatitude, longitude: brestLongitude)
}

// MARK: - ShowStudentButton

class ShowStudentButton: UIButton {

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupUI()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    private func setupUI() {
        self.setTitle(Constants.showStudentsButtonTitle, for: .normal)
        self.backgroundColor = Constants.showStudentsButtonBackgroundColor
        self.translatesAutoresizingMaskIntoConstraints = false
    }
}
