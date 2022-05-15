//
//  MainViewController.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import UIKit
import SnapKit

// MARK: - Constants
private extension Constants {
	enum TitleLabel {
		static let topIndent: CGFloat = 40
		static let leadingIndent: CGFloat = 40
	}

	enum Stack {
		static let leadingTrailingIndent: CGFloat = 10
		static let topIndent: CGFloat = 250
		static let bottomIndent: CGFloat = 350
	}
}

// MARK: - MainViewController
class MainViewController: UIViewController {
	
	// MARK: - Properties
	private let backgroundImageView = UIImageView()
	
	private var viewModel: MainViewModelInterface
	
	private lazy var titleLabel: UILabel = {
		let label = UILabel()
		label.numberOfLines = 0
		label.backgroundColor = .green.withAlphaComponent(0.4)
		label.textAlignment = .center
		label.font = .systemFont(ofSize: 28)
		return label
	}()
	
	private lazy var stack: UIStackView = {
		let stack = UIStackView()
		stack.alignment = .center
		stack.distribution = .fillEqually
		stack.axis = .horizontal
		stack.spacing = 30
		return stack
	}()
	
	private lazy var cppInfoButton: UIButton = {
		let button = UIButton()
		button.addTarget(self, action: #selector(cppInfoButtonDidTaped), for: .touchUpInside)
		button.setImage(.init(named: LanguageType.cpp.languageImageName), for: .normal)
		return button
	}()
	
	private lazy var swiftInfoButton: UIButton = {
		let button = UIButton()
		button.addTarget(self, action: #selector(swiftInfoButtonDidTaped), for: .touchUpInside)
		button.setImage(.init(named: LanguageType.swift.languageImageName), for: .normal)
		return button
	}()
	
	private lazy var javaInfoButton: UIButton = {
		let button = UIButton()
		button.addTarget(self, action: #selector(javaInfoButtonDidTaped), for: .touchUpInside)
		button.setImage(.init(named: LanguageType.java.languageImageName), for: .normal)
		return button
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
		view.backgroundColor = .purple
		setupUI()
		configure()
	}
	
	// MARK: - Setups
	private func setupUI() {
		//setupBackgroundImageView()
		setupTitleLabel()
		setupStack()
	}
	
	private func setupBackgroundImageView() {
		view.addSubview(backgroundImageView)
		
		backgroundImageView.snp.makeConstraints { make in
			make.edges.equalToSuperview()
		}
	}
	
	private func setupTitleLabel() {
		view.addSubview(titleLabel)
		
		titleLabel.snp.makeConstraints { make in
			make.top.equalToSuperview().inset(Constants.TitleLabel.topIndent)
			make.leading.trailing.equalToSuperview().inset(Constants.TitleLabel.leadingIndent)
		}
	}
	
	private func setupStack() {
		view.addSubview(stack)
		
		stack.snp.makeConstraints { make in
			make.leading.trailing.equalToSuperview().inset(Constants.Stack.leadingTrailingIndent)
			make.top.equalToSuperview().inset(Constants.Stack.topIndent)
			make.bottom.equalToSuperview().inset(Constants.Stack.bottomIndent)
		}
		
		stack.addArrangedSubview(javaInfoButton)
		stack.addArrangedSubview(cppInfoButton)
		stack.addArrangedSubview(swiftInfoButton)
	}
	
	// MARK: - Configure
	private func configure() {
		titleLabel.text = viewModel.titleLabelText
		backgroundImageView.image = .init(named: viewModel.backgroundImageName)
	}
	
	// MARK: - Buttons Actions
	@objc
	private func cppInfoButtonDidTaped() {
		viewModel.cppInfoButtonDidTaped()
	}
	
	@objc
	private func swiftInfoButtonDidTaped() {
		viewModel.swiftInfoButtonDidTaped()
	}
	
	@objc
	private func javaInfoButtonDidTaped() {
		viewModel.javaInfoButtonDidTaped()
	}
}
