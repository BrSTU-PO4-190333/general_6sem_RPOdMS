//
//  SetupRoutesButton.swift
//  Lesson24
//
//  Created by Harbros 109 on 22.01.22.
//

import UIKit

// MARK: - Constants

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"

    static let routesButtonTitle = "Create Routes"
    static let routesButtonBackgroundColor = UIColor.blue
}

class SetupRoutesButton: UIButton {

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupUI()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    private func setupUI() {
        self.setTitle(Constants.routesButtonTitle, for: .normal)
        self.backgroundColor = Constants.routesButtonBackgroundColor
        self.translatesAutoresizingMaskIntoConstraints = false
    }
}
