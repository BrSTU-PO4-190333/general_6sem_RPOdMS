//
//  CreatingPhotoViewController.swift
//  ModilkaLab9(media)
//
//  Created by Kiril Krechko on 12.04.22.
//

import UIKit

// MARK: - Constants
private extension Constants {
	enum CreatedImage {
		static let topInset: CGFloat = 120
		static let trailingLeadingInset: CGFloat = 15
		static let bottomInset: CGFloat = 200
	}
}

// MARK: - CreatingPhotoViewController
class CreatingPhotoViewController: UIViewController {
	// MARK: - Properties
	private let backgroundImageView = UIImageView()
	private let createdImageView = UIImageView()
	private let createPictureButton = UIButton()
	
	private let viewModel: CreatingPhotoViewModel
	
	// MARK: - Init
	init(viewModel: CreatingPhotoViewModel) {
		self.viewModel = viewModel
		super.init(nibName: nil, bundle: nil)
	}
	
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
		setupBackgroundImageView()
		setupCreatedImageView()
		setupCreatePictureButton()
	}
	
	private func setupBackgroundImageView() {
		view.addSubview(backgroundImageView)
		
		backgroundImageView.snp.makeConstraints { make in
			make.edges.equalToSuperview()
		}
	}
	
	private func setupCreatedImageView() {
		view.addSubview(createdImageView)
		
		createdImageView.snp.makeConstraints { make in
			make.top.equalToSuperview().inset(Constants.CreatedImage.topInset)
			make.trailing.leading.equalToSuperview().inset(Constants.CreatedImage.trailingLeadingInset)
			make.bottom.equalToSuperview().inset(Constants.CreatedImage.bottomInset)
		}
	}
	
	private func setupCreatePictureButton() {
		view.addSubview(createPictureButton)
		
		createPictureButton.snp.makeConstraints { make in
			make.top.equalTo(createdImageView.snp.bottom).inset(-35)
			make.trailing.leading.equalToSuperview().inset(50)
			make.bottom.equalToSuperview().inset(95)
		}
	}
	
	// MARK: - Configure
	private func configure() {
		backgroundImageView.image = .init(named: Constants.backgroundImageName)
		createdImageView.backgroundColor = .blue.withAlphaComponent(0.4)
		createdImageView.layer.cornerRadius = 14
		
		createPictureButton.backgroundColor = .white
		createPictureButton.layer.cornerRadius = 14
		createPictureButton.setImage(.init(systemName: "camera"), for: .normal)
		createPictureButton.addTarget(self, action: #selector(createPictureButtonDidTap), for: .touchUpInside)
	}
	
	// MARK: - Actions
	@objc
	private func createPictureButtonDidTap() {
		let picker = UIImagePickerController()
		picker.sourceType = .camera
		picker.delegate = self
		present(picker, animated: true)
	}
}

// MARK: - UIImagePickerControllerDelegate, UINavigationControllerDelegate
extension CreatingPhotoViewController: UIImagePickerControllerDelegate, UINavigationControllerDelegate {
	func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
		picker.dismiss(animated: true, completion: nil)
	}
	
	func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
		picker.dismiss(animated: true, completion: nil)
		guard let image = info[UIImagePickerController.InfoKey.originalImage] as? UIImage else { return }
		createdImageView.image = image
	}
}


