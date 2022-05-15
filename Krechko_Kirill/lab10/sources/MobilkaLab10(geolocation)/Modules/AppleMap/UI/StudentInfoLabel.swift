//
//  StudentInfoLabel.swift
//  Lesson24
//
//  Created by Harbros 109 on 22.01.22.
//

import UIKit

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"
    static let studentInfoViewBackgroundColor: UIColor = .init(red: 0, green: 0, blue: 255, alpha: 0.3)
}

class StudentInfoLabel: UILabel {

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupUI()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    private func setupUI() {
        self.backgroundColor = Constants.studentInfoViewBackgroundColor
        self.sizeToFit()
        self.numberOfLines = .zero
        self.translatesAutoresizingMaskIntoConstraints = false
    }
}
