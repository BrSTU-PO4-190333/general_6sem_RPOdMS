//
//  StudentCell.swift
//  Lesson24
//
//  Created by Harbros 109 on 3.01.22.
//

import UIKit

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"

    static let titleLabelLeadingIndent: CGFloat = 10
    static let titleLabelTrailingIndent: CGFloat = -10
}

// MARK: - StudentInfoModel

struct StudentInfoModel {
    var title: String
    var imageName: String?
}

// MARK: - StudentInfoCell

class StudentInfoCell: UITableViewCell {

    private let titleLabel = UILabel()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupUI()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    private func setupUI() {
        addSubview(titleLabel)
        setupConstraints()
        titleLabel.numberOfLines = .zero
    }

    private func setupConstraints() {
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            titleLabel.leadingAnchor.constraint(
                equalTo: self.leadingAnchor,
                constant: Constants.titleLabelLeadingIndent
            ),
            titleLabel.trailingAnchor.constraint(
                equalTo: self.trailingAnchor,
                constant: Constants.titleLabelTrailingIndent
            ),
            titleLabel.topAnchor.constraint(equalTo: self.topAnchor),
            titleLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor)
        ])
    }

    func setup(with model: String) {
        titleLabel.text = model
    }
}
