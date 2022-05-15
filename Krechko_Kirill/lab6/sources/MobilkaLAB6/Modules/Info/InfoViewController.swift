//
//  InfoViewController.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import UIKit

// MARK: - Constants
private extension Constants {
	enum LanguageImage {
		static let topIndent: CGFloat = 100
		static let size: CGFloat = 140
	}
	
	enum LanguageInfoLabel {
		static let topIndent: CGFloat = -10
		static let trailingLeadingIndent: CGFloat = 30
		static let bottomIndent: CGFloat = 40
	}
}

// MARK: - InfoViewController
class InfoViewController: UIViewController {
	
	// MARK: - Properties
	private let backgroundImageView = UIImageView()
	private let languageImageView = UIImageView()

	private var viewModel: InfoViewModelInterface
	
	private lazy var languageInfoLabel: UILabel = {
		let label = UILabel()
		label.textAlignment = .center
		label.textColor = .green
		label.numberOfLines = 0
		label.font = .systemFont(ofSize: 25)
		label.adjustsFontSizeToFitWidth = true
		label.backgroundColor = .gray.withAlphaComponent(0.5)
		return label
	}()
	
	// MARK: - Init
	init(viewModel: InfoViewModelInterface) {
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
		viewModel.viewDidLoad()
		setupUI()
		configure()
	}
	
	// MARK: - Setups
	private func setupUI() {
		setupBackgroundImage()
		setupLanguageImageView()
		setupLanguageInfoLabel()
	}
	
	private func setupBackgroundImage() {
		view.addSubview(backgroundImageView)
		backgroundImageView.snp.makeConstraints { make in
			make.edges.equalToSuperview()
		}
	}
	
	private func setupLanguageImageView() {
		view.addSubview(languageImageView)
		languageImageView.snp.makeConstraints { make in
			make.top.equalToSuperview().inset(Constants.LanguageImage.topIndent)
			make.centerX.equalToSuperview()
			make.size.equalTo(Constants.LanguageImage.size)
		}
	}
	
	private func setupLanguageInfoLabel() {
		view.addSubview(languageInfoLabel)
		languageInfoLabel.snp.makeConstraints { make in
			make.top.equalTo(languageImageView.snp.bottom)
				.inset(Constants.LanguageInfoLabel.topIndent)
			make.trailing.leading.equalToSuperview()
				.inset(Constants.LanguageInfoLabel.trailingLeadingIndent)
			make.bottom.equalToSuperview().inset(Constants.LanguageInfoLabel.bottomIndent)
		}
	}
	
	// MARK: - Configure
	private func configure() {
		backgroundImageView.image = .init(named: viewModel.backgroundImageName)
		languageImageView.image = .init(named: viewModel.languageImageName)
		languageInfoLabel.text = viewModel.languageInfo
	}
}
