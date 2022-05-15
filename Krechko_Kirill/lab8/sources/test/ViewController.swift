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
	private let backgroundImageView = UIImageView()
	private let canvas = Canvas()
	
	// MARK: - Life cycle
	override func viewDidLoad() {
		super.viewDidLoad()
		setupBackgroundImageView()
		setupCustomGestures()
		setupDrawer()
	}
	
	override func viewDidLayoutSubviews() {
		super.viewDidLayoutSubviews()
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
		canvas.backgroundColor = .systemMint
	}
	
	// MARK: - Actions
	@objc
	private func leftParenthesisGestureDidCommit() {
		canvas.backgroundColor = .clear
		backgroundImageView.image = .init(named: "winter")
	}
	
	@objc
	private func rightParenthesisGestureDidCommit() {
		canvas.backgroundColor = .clear
		backgroundImageView.image = .init(named: "summer")
	}
	
	@objc
	private func checkmarkGestureDidCommit() {
		canvas.backgroundColor = .clear
		backgroundImageView.image = .init(named: "spring")
	}
}

