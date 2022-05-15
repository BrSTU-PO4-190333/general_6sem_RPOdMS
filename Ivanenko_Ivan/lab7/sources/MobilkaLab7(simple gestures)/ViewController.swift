//
//  ViewController.swift
//  MobilkaLab7(simple gestures)
//
//  Created by Kiril Krechko on 4.04.22.
//

import UIKit
import SnapKit

// MARK: - Constants
private enum Constants {
	static let touchDownInfo = "A touch-down event in the control"
	static let touchDownRepeatInfo = "A repeated touch-down event in the control; for this event the value of the UITouch tapCount method is greater than one"
	static let touchDragOutsideInfo = "An event where a finger is dragged just outside the bounds of the control"
	
	static let backgroundImageName = "winter-tree"
	
	enum TableView {
		static let identifier = "tableView"
		static let numberOfRowsInSection = 10
		static let topIndent: CGFloat = -30
		static let trailingLeadingIndent: CGFloat = 20
		static let bottomIndent: CGFloat = 10
	}
	
	enum BtnTypeInfoLabel {
		static let topIndent: CGFloat = 40
		static let trailingLeadingIndent: CGFloat = 40
		static let height: CGFloat = 100
	}
	
	enum StackView {
		static let topIndent: CGFloat = -20
		static let trailingLeadingIndent: CGFloat = 60
	}
}

// MARK: - BtnTargetsParameters
private struct BtnTargetsParameters {
	let action: Selector
	let event: UIControl.Event
	let eventDescription: String
}

// MARK: - ViewController
class ViewController: UIViewController {
	// MARK: - Properties
	private let buttonsTargets: [BtnTargetsParameters] = [
		.init(action: #selector(touchDownButtonDidTaped), event: .touchDown, eventDescription: "Одиночное нажатие"),
		.init(action: #selector(touchDownRepeatButtonDidTaped), event: .touchDownRepeat, eventDescription: "Двойное нажатие"),
		.init(action: #selector(touchDragOutsideDidTaped), event: .touchDragOutside, eventDescription: "Нажатие с выходом за пределы кнопки")
	]
	
	private let backgroundImageView = UIImageView()
	private let tableView = UITableView()
	
	private lazy var btnTypeInfoLabel: UILabel = {
		let label = UILabel()
		label.textAlignment = .center
		label.font = .systemFont(ofSize: 20)
		label.numberOfLines = 0
		label.textColor = .black
		label.backgroundColor = .white.withAlphaComponent(0.7)
		return label
	}()
	
	private lazy var stackView: UIStackView = {
		let stack = UIStackView()
		stack.axis = .horizontal
		stack.spacing = 10
		stack.distribution = .fillEqually
		return stack
	}()
	
	// MARK: - Life cycle
	override func viewDidLoad() {
		super.viewDidLoad()
		setupUI()
	}
	
	// MARK: - Setups
	private func setupUI() {
		view.backgroundColor = .purple
		// setupBackgroundImageView()
		setupBtnTypeInfoLabel()
		setupTableView()
		setupStackView()
		setupButtons()
	}
	
	private func setupBackgroundImageView() {
		view.addSubview(backgroundImageView)
		backgroundImageView.snp.makeConstraints { make in
			make.edges.equalToSuperview()
		}
		backgroundImageView.image = .init(named: Constants.backgroundImageName)
	}
	
	private func setupBtnTypeInfoLabel() {
		view.addSubview(btnTypeInfoLabel)
		btnTypeInfoLabel.snp.makeConstraints { make in
			make.top.equalToSuperview().inset(Constants.BtnTypeInfoLabel.topIndent)
			make.trailing.leading.equalToSuperview().inset(Constants.BtnTypeInfoLabel.trailingLeadingIndent)
			make.height.equalTo(Constants.BtnTypeInfoLabel.height)
		}
	}
	
	private func setupStackView() {
		view.addSubview(stackView)
		stackView.snp.makeConstraints { make in
			make.top.equalTo(tableView.snp.bottom).inset(-50)
			make.trailing.leading.equalToSuperview().inset(5)
			make.height.equalTo(100)
		}
	}
	
	private func setupButtons() {
		buttonsTargets.forEach { item in
			let actionButton = UIButton()
			actionButton.backgroundColor = .black
			actionButton.layer.cornerRadius = 8
			actionButton.addTarget(self, action: item.action, for: item.event)
			actionButton.setTitle(item.eventDescription, for: .normal)
			actionButton.titleLabel?.numberOfLines = 0
			actionButton.titleLabel?.textAlignment = .center
			stackView.addArrangedSubview(actionButton)
		}
	}
	
	private func setupTableView() {
		view.addSubview(tableView)
		tableView.snp.makeConstraints { make in
			make.top.equalTo(btnTypeInfoLabel.snp.bottom).inset(-50)
			make.trailing.leading.equalToSuperview().inset(Constants.TableView.trailingLeadingIndent)
			make.height.equalTo(200)
		}
		tableView.delegate = self
		tableView.dataSource = self
		tableView.register(UITableViewCell.self, forCellReuseIdentifier: Constants.TableView.identifier)
		tableView.backgroundColor = .black
	}
	
	// MARK: - Actions
	@objc
	private func touchDownButtonDidTaped() {
		btnTypeInfoLabel.text = Constants.touchDownInfo
		btnTypeInfoLabel.adjustsFontSizeToFitWidth = true
	}
	
	@objc
	private func touchDownRepeatButtonDidTaped() {
		btnTypeInfoLabel.text = Constants.touchDownRepeatInfo
		btnTypeInfoLabel.adjustsFontSizeToFitWidth = true
	}
	
	@objc
	private func touchDragOutsideDidTaped() {
		btnTypeInfoLabel.text = Constants.touchDragOutsideInfo
		btnTypeInfoLabel.adjustsFontSizeToFitWidth = true
	}
}

// MARK: - UITableViewDataSource
extension ViewController: UITableViewDataSource {
	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return Constants.TableView.numberOfRowsInSection
	}
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		let cell = tableView.dequeueReusableCell(withIdentifier: Constants.TableView.identifier, for: indexPath)
		cell.selectionStyle = .none
		cell.backgroundColor = .black
		cell.textLabel?.textAlignment = .center
		cell.textLabel?.backgroundColor = .black
		cell.textLabel?.textColor = .white
		cell.textLabel?.text = "Ячейка для свайпа влево-вправо"
		cell.textLabel?.numberOfLines = 0
		return cell
	}
}

// MARK: - UITableViewDelegate
extension ViewController: UITableViewDelegate {
	func tableView(_ tableView: UITableView, leadingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
		tableView.cellForRow(at: indexPath)?.backgroundColor = .systemMint
		return nil
	}
	
	func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
		tableView.cellForRow(at: indexPath)?.backgroundColor = .systemRed
		return nil
	}
}
