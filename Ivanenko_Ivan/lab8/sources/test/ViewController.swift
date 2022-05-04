//
//  ViewController.swift
//  test
//
//  Created by Kiril Krechko on 6.04.22.
//

import UIKit
import UIKit.UIGestureRecognizerSubclass

// MARK: - ViewController
class ViewController: UIViewController {
	
	// MARK: - Properties
	private let label = UILabel()
	private let backgroundImageView = UIImageView()
	private let canvas = Canvas()
	
	// MARK: - Life cycle
	override func viewDidLoad() {
		super.viewDidLoad()
		// setupBackgroundImageView()
		setupCustomGestures()
		setupDrawer()
		view.addSubview(label)
	}
	
	
	override func viewDidLayoutSubviews() {
		super.viewDidLayoutSubviews()
		label.frame = .init(x: view.center.x - 100, y: 50, width: 200, height: 50)
		label.backgroundColor = .black.withAlphaComponent(0.5)
		label.textColor = .white
		label.textAlignment = .center
		backgroundImageView.frame = view.frame
		canvas.frame = view.bounds
	}
	
	// MARK: - Setups
	private func setupBackgroundImageView() {
		view.addSubview(backgroundImageView)
	}
	
	private func setupCustomGestures() {
		view.addGestureRecognizer(LeftParenthesisGestureRecognizer(
			target: self, action: #selector(leftParenthesisGestureDidCommit))
		)
		
		view.addGestureRecognizer(RightParenthesisGestureRecognizer(
			target: self, action: #selector(rightParenthesisGestureDidCommit))
		)
		
		view.addGestureRecognizer(CheckmarkGestureRecognizer(
			target: self, action: #selector(checkmarkGestureDidCommit))
		)
	}
	
	private func setupDrawer() {
		view.addSubview(canvas)
		canvas.backgroundColor = .purple
	}
	
	// MARK: - Actions
	@objc
	private func leftParenthesisGestureDidCommit() {
		label.text = "знак >"
	}
	
	@objc
	private func rightParenthesisGestureDidCommit() {
		label.text = "знак <"
	}
	
	@objc
	private func checkmarkGestureDidCommit() {
		label.text = "галочка"
	}
}

