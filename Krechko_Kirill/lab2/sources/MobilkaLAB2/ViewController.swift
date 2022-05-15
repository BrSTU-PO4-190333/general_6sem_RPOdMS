//
//  ViewController.swift
//  MobilkaLAB2
//
//  Created by Kiril Krechko on 24.02.22.
//

import UIKit
import SnapKit

// MARK: - ViewController
class ViewController: UIViewController {
	// MARK: - Properties
	private let titleLabel: UILabel = {
		let label = UILabel()
		label.text = "Пожалуйста введите число от 1 до 100"
		label.textColor = .white
		label.textAlignment = .center
		label.numberOfLines = 0
		return label
	}()
	
	private let inputNumberTextField: UITextField = {
		let textField = UITextField()
		textField.placeholder = "Число:"
		textField.textColor = .black
		textField.textAlignment = .center
		textField.backgroundColor = .white
		textField.layer.borderColor = UIColor.black.cgColor
		textField.layer.cornerRadius = 5
		return textField
	}()
	
	private let startButton: UIButton = {
		let button = UIButton()
		button.setTitle("Угадать", for: .normal)
		button.backgroundColor = .white
		button.setTitleColor(.black, for: .normal)
		button.layer.borderColor = UIColor.black.cgColor
		return button
	}()
	
	private let resultLabel: UILabel = {
		let label = UILabel()
		label.font = .systemFont(ofSize: 20)
		return label
	}()
	
	private let answerButton: UIButton = {
		let button = UIButton()
		button.backgroundColor = .white
		button.setTitleColor(.black, for: .normal)
		return button
	}()
	
	private let leftHiddenAnswerLabel: UILabel = {
		let label = UILabel()
		label.backgroundColor = .yellow
		label.text = "Отв"
		label.textAlignment = .right
		label.adjustsFontSizeToFitWidth = true
		return label
	}()
	
	private let rightHiddenAnswerLabel: UILabel = {
		let label = UILabel()
		label.backgroundColor = .yellow
		label.text = "ет:"
		label.textAlignment = .left
		label.adjustsFontSizeToFitWidth = true
		return label
	}()
	
	private var hiddenNumber = Int.random(in: (1...100))
	private var isAnswerHidden = true
	
	// MARK: - Life cycle
	override func viewDidLoad() {
		super.viewDidLoad()
		view.backgroundColor = .systemMint
		setupUI()
	}
	
	// MARK: - Setups methods
	private func setupUI() {
		setupTitleLabel()
		setupInputNumberTextField()
		setupStartButton()
		setupResultLabel()
		setupAnswerButton()
		setupLeftHiddenAnswerLabel()
		setupRightHiddenAnswerLabel()
	}
	
	private func setupTitleLabel() {
		view.addSubview(titleLabel)
		titleLabel.snp.makeConstraints { make in
			make.top.equalTo(50)
			make.leading.trailing.equalToSuperview().inset(16)
			make.centerX.equalToSuperview()
		}
	}
	
	private func setupInputNumberTextField() {
		view.addSubview(inputNumberTextField)
		
		inputNumberTextField.snp.makeConstraints { make in
			make.top.equalTo(titleLabel.snp.bottom).inset(-30)
			make.centerX.equalToSuperview()
			make.leading.trailing.equalToSuperview().inset(16)
			make.height.equalTo(40)
		}
		inputNumberTextField.delegate = self
	}
	
	private func setupStartButton() {
		view.addSubview(startButton)
		startButton.snp.makeConstraints { make in
			make.top.equalTo(inputNumberTextField.snp.bottom).inset(-30)
			make.centerX.equalToSuperview()
			make.width.equalTo(100)
		}
		startButton.addTarget(self, action: #selector(startButtonPressed), for: .touchUpInside)
	}
	
	private func setupResultLabel() {
		view.addSubview(resultLabel)
		
		resultLabel.snp.makeConstraints { make in
			make.top.equalTo(startButton.snp.bottom).inset(-50)
			make.centerX.equalToSuperview()
		}
	}
	
	private func setupAnswerButton() {
		view.addSubview(answerButton)
		answerButton.snp.makeConstraints { make in
			make.bottom.equalToSuperview().inset(30)
			make.trailing.equalToSuperview().inset(60)
			make.width.equalTo(60)
			make.height.equalTo(70)
		}
		answerButton.addTarget(self, action: #selector(answerButtonPressed), for: .touchUpInside)
	}
	
	private func setupLeftHiddenAnswerLabel() {
		answerButton.addSubview(leftHiddenAnswerLabel)
		setLeftHiddenAnswerLabelBeginConstraints()
	}
	
	private func setLeftHiddenAnswerLabelBeginConstraints() {
		leftHiddenAnswerLabel.snp.makeConstraints { make in
			make.top.left.height.equalToSuperview()
			make.width.equalToSuperview().multipliedBy(0.5)
		}
	}
	
	private func setupRightHiddenAnswerLabel() {
		answerButton.addSubview(rightHiddenAnswerLabel)
		setRightHiddenAnswerLabelBeginConstraints()
	}
	
	private func setRightHiddenAnswerLabelBeginConstraints() {
		rightHiddenAnswerLabel.snp.makeConstraints { make in
			make.top.trailing.height.equalToSuperview()
			make.width.equalToSuperview().multipliedBy(0.5)
		}
	}
	
	// MARK: - Actions
	@objc
	private func startButtonPressed() {
		guard let answerStrigng = inputNumberTextField.text,
			  let answer = Int(answerStrigng)
		else { return }
		
		guard answer != hiddenNumber else {
			view.backgroundColor = .green
			resultLabel.text = "Вы угадали! Число обновилось :)"
			hiddenNumber = Int.random(in: 1...100)
			hideAnswer()
			return
		}
		
		view.backgroundColor = .red

		guard answer >= 1 && answer <= 100 else {
			resultLabel.text = "Некорректный ввод числа"
			return
		}
		
		guard answer > hiddenNumber else  {
			resultLabel.text = "Недолет"
			return
		}
		resultLabel.text = "Перелет"
	}
	
	@objc
	private func answerButtonPressed() {
		isAnswerHidden ? showAnswer() : hideAnswer()
		isAnswerHidden.toggle()
	}
	
	private func showAnswer() {
		leftHiddenAnswerLabel.snp.remakeConstraints { make in
			make.top.height.equalToSuperview()
			make.width.equalToSuperview().multipliedBy(0.5)
			make.trailing.equalTo(answerButton.snp.leading)
		}
		
		rightHiddenAnswerLabel.snp.remakeConstraints { make in
			make.top.height.equalToSuperview()
			make.width.equalToSuperview().multipliedBy(0.5)
			make.leading.equalTo(answerButton.snp.trailing)
		}
		answerButton.setTitle("\(hiddenNumber)", for: .normal)
	}
	
	private func hideAnswer() {
		setLeftHiddenAnswerLabelBeginConstraints()
		setRightHiddenAnswerLabelBeginConstraints()
		answerButton.setTitle("", for: .normal)
	}
}

// MARK: - UITextFieldDelegate
extension ViewController: UITextFieldDelegate {
	func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange,
				   replacementString string: String) -> Bool {
		
		view.backgroundColor = .systemMint
		if string == "" { return true }
		return (textField.text?.count ?? .init()) < 3 ? true : false
	}
}
