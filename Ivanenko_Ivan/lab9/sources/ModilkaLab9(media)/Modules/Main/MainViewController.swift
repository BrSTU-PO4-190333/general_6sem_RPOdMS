//
//  MainViewController.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import UIKit
import SnapKit

// MARK: - Constants
enum Constants {
	static let backgroundImageName = "musi"
}

// MARK: - Constants
private extension Constants {
	enum Stack {
		static let topBottomInsent: CGFloat = 175
		static let trailingLeadingInset: CGFloat = 20
	}
}

// MARK: - ButtonsActions
private struct ButtonsActions {
	let action: Selector
	let actionDescription: String
}

// MARK: - MainViewController
class MainViewController: UIViewController {
	
	// MARK: - Properties
	private let viewModel: MainViewModelInterface
	private let backgroundImageView = UIImageView()
	private let buttonsActions: [ButtonsActions] = [
		.init(action: #selector(showVideoButtonDidTaped), actionDescription: "Show Video"),
		.init(action: #selector(showAudioButtonDidTaped), actionDescription: "Show Audio"),
		.init(action: #selector(getPictureButtonDidTaped), actionDescription: "Get Picture"),
		.init(action: #selector(showGalleryButtonDidTaped), actionDescription: "Show Gallery")
	]
		
	private lazy var stackView: UIStackView = {
		let stack = UIStackView()
		stack.alignment = .center
		stack.distribution = .fillEqually
		stack.axis = .horizontal
		stack.spacing = 10
		return stack
	}()

	
	// MARK: - Init
	init(viewModel: MainViewModelInterface) {
		self.viewModel = viewModel
		super.init(nibName: nil, bundle: nil)
	}

	@available(*, unavailable)
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	// MARK: - Life cycle
	override func viewDidLoad() {
		super.viewDidLoad()
		setupUI()
		configure()
	}
	
	// MARK: - Setups
	private func setupUI() {
		//setupBackgroundImageView()
		view.backgroundColor = .purple
		setupStack()
		setupButtons()
	}
	
	private func setupBackgroundImageView() {
		view.addSubview(backgroundImageView)
		
		backgroundImageView.snp.makeConstraints { make in
			make.edges.equalToSuperview()
		}
	}
	
	private func setupStack() {
		view.addSubview(stackView)
		stackView.snp.makeConstraints { make in
			make.top.bottom.equalToSuperview().inset(Constants.Stack.topBottomInsent)
			make.trailing.leading.equalToSuperview().inset(10)
		}
	}
	
	private func setupButtons() {
		buttonsActions.forEach { item in
			let actionButton = UIButton()
			actionButton.backgroundColor = .black
			actionButton.setTitleColor(.white, for: .normal)
			actionButton.addTarget(self, action: item.action, for: .touchUpInside)
			actionButton.setTitle(item.actionDescription, for: .normal)
			actionButton.titleLabel?.textAlignment = .center
			actionButton.titleLabel?.numberOfLines = 0
			stackView.addArrangedSubview(actionButton)
			actionButton.snp.makeConstraints { make in
				make.height.equalTo(50)
			}

		}
	}
	
	// MARK: - Configure
	private func configure() {
		backgroundImageView.image = .init(named: Constants.backgroundImageName)
	}
	
	// MARK: - Buttons Actions
	@objc
	private func showVideoButtonDidTaped() {
		viewModel.showVideo()
	}
	
	@objc
	private func showAudioButtonDidTaped() {
		viewModel.playMusic()
	}
	
	@objc
	private func getPictureButtonDidTaped() {
		viewModel.createPhoto()
	}
	
	@objc
	private func showGalleryButtonDidTaped() {
		viewModel.openGallery()
	}
}
