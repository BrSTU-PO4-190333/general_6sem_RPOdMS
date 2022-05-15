//
//  MainViewController.swift
//  Lesson20.1
//
//  Created by Harbros 109 on 7.12.21.
//

import UIKit

// MARK: - Constants

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"
    static let viewBackgroundColor = UIColor.black

    static let buttonsColor = UIColor.systemMint

    static let openAppleMapButtonTitle = "Apple Map"
    static let openGoogleMapButtonTitle = "Google Map"
}

// MARK: - MainViewControllerInterface

protocol MainViewControllerInterface: AnyObject { }

// MARK: - MainViewController

class MainViewController: UIViewController {

    let openAppleMapButton = UIButton()
    let openGoogleMapButton = UIButton()

    let viewModel: MainViewModelInterface

    init(viewModel: MainViewModelInterface) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.viewDidLoad()
        view.backgroundColor = Constants.viewBackgroundColor

        setupOpenAppleMapButton()
        // setupOpenGoogleMapButton()
    }

    // MARK: - Apple Map
    private func setupOpenAppleMapButton() {
        view.addSubview(openAppleMapButton)
        openAppleMapButton.setTitle(Constants.openAppleMapButtonTitle, for: .normal)
        openAppleMapButton.setTitleColor(Constants.buttonsColor, for: .normal)

        setupOpenAppleMapButtonConstraints()
        openAppleMapButton.addTarget(self, action: #selector(openAppleMapButtonPressed), for: .touchUpInside)
    }

    private func setupOpenAppleMapButtonConstraints() {
        openAppleMapButton.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            openAppleMapButton.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            openAppleMapButton.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            openAppleMapButton.topAnchor.constraint(
                equalTo: view.topAnchor,
                constant: view.frame.height / 3
            )
        ])
    }

    @objc
    private func openAppleMapButtonPressed() {
        viewModel.openAppleMapButtonPressed()
    }

    // MARK: - Google Map
    private func setupOpenGoogleMapButton() {
        view.addSubview(openGoogleMapButton)
        openGoogleMapButton.setTitle(Constants.openGoogleMapButtonTitle, for: .normal)
        openGoogleMapButton.setTitleColor(Constants.buttonsColor, for: .normal)

        setupOpenGoogleMapButtonConstraints()
        openGoogleMapButton.addTarget(self, action: #selector(openGoogleMapButtonPressed), for: .touchUpInside)
    }

    private func setupOpenGoogleMapButtonConstraints() {
        openGoogleMapButton.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            openGoogleMapButton.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            openGoogleMapButton.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            openGoogleMapButton.bottomAnchor.constraint(
                equalTo: view.bottomAnchor,
                constant: -(view.frame.height / 3)
            )
        ])
    }

    @objc
    private func openGoogleMapButtonPressed() {
        viewModel.openGoogleMapButtonPressed()
    }
}

extension MainViewController: MainViewControllerInterface { }
