//
//  ViewController.swift
//  MobilkaLAB3
//
//  Created by Kiril Krechko on 23.03.22.
//

import UIKit
import SnapKit

// MARK: - ViewController
class ViewController: UIViewController {
	// MARK: - Propeties
	private let backgroundImage = UIImageView()
	private let mainImageView = UIImageView()

	private lazy var textField: UITextField = {
		let textField = UITextField()
		textField.placeholder = "Enter your text here"
		textField.backgroundColor = .systemMint
		textField.borderStyle = .bezel
		textField.textColor = .black
		return textField
	}()
	
	private lazy var searchButton: UIButton = {
		let button = UIButton()
		button.setTitle("Go!", for: .normal)
		button.backgroundColor = .gray.withAlphaComponent(0.6)
		return button
	}()
	
	private lazy var likeButton: UIButton = {
		let button = UIButton()
		button.setImage(.init(named: "like"), for: .normal)
		button.backgroundColor = .systemMint
		button.layer.cornerRadius = 7
		return button
	}()
	
	private lazy var dislikeButton: UIButton = {
		let button = UIButton()
		button.setImage(.init(named: "dislike"), for: .normal)
		button.backgroundColor = .systemMint
		button.layer.cornerRadius = 7
		return button
	}()
	
	// MARK: - Life cycle
	override func viewDidLoad() {
		super.viewDidLoad()
		setupUI()
	}
	
	// MARK: - Setups
	private func setupUI() {
		setupBackgroundImage()
		setupTextField()
		setupSearchButton()
		setupMainImageView()
		setupLikeButton()
		setupDislikeButton()
	}
	
	private func setupBackgroundImage() {
		view.addSubview(backgroundImage)
		
		backgroundImage.snp.makeConstraints { make in
			make.edges.equalToSuperview()
		}
		backgroundImage.image = .init(named: "background")
	}
	
	private func setupTextField() {
		view.addSubview(textField)
		
		textField.snp.makeConstraints { make in
			make.leading.equalToSuperview().inset(50)
			make.trailing.equalToSuperview().inset(150)
			make.top.equalToSuperview().inset(100)
			make.height.equalTo(30)
		}
	}
	
	private func setupSearchButton() {
		view.addSubview(searchButton)
		
		searchButton.snp.makeConstraints { make in
			make.top.equalToSuperview().inset(100)
			make.trailing.equalToSuperview().inset(70)
			make.leading.equalTo(textField.snp.trailing).inset(-10)
			make.height.equalTo(textField.snp.height)
		}
	}
	
	private func setupMainImageView() {
		view.addSubview(mainImageView)
		
		mainImageView.snp.makeConstraints { make in
			make.top.equalTo(textField.snp.bottom).inset(-40)
			make.trailing.leading.equalToSuperview().inset(50)
			make.height.equalTo(400)
		}
		
		mainImageView.image = .init(named: "puppy")
	}
	
	private func setupLikeButton() {
		view.addSubview(likeButton)
		
		likeButton.snp.makeConstraints { make in
			make.top.equalTo(mainImageView.snp.bottom).inset(-40)
			make.leading.equalTo(40)
			make.width.height.equalTo(100)
		}
	}
	
	private func setupDislikeButton() {
		view.addSubview(dislikeButton)
		
		dislikeButton.snp.makeConstraints { make in
			make.top.equalTo(mainImageView.snp.bottom).inset(-40)
			make.trailing.equalTo(-40)
			make.width.height.equalTo(100)
		}
	}
}

