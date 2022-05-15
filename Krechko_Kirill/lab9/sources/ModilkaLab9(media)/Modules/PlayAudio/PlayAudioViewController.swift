//
//  PlayAudioViewController.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import AVFoundation
import UIKit
import SnapKit

// MARK: - Constants
private extension Constants {
	enum PlayingButton {
		static let widthHeight: CGFloat = 75
	}
}

// MARK: - PlayAudioViewController
class PlayAudioViewController: UIViewController {
	
	// MARK: - Properties
	private let viewModel: PlayAudioViewModelInterface
	
	private let backgroundImageView = UIImageView()
	private let playingButton = UIButton()
	
	private var player: AVAudioPlayer?
	
	// MARK: - Init
	init(viewModel: PlayAudioViewModelInterface) {
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
		setupBackgroundImageView()
		setupPlayingButton()
	}
	
	private func setupBackgroundImageView() {
		view.addSubview(backgroundImageView)
		
		backgroundImageView.snp.makeConstraints { make in
			make.edges.equalToSuperview()
		}
	}
	
	private func setupPlayingButton() {
		view.addSubview(playingButton)
		playingButton.snp.makeConstraints { make in
			make.center.equalToSuperview()
			make.size.equalTo(Constants.PlayingButton.widthHeight)
		}
		playingButton.backgroundColor = .white
		playingButton.layer.cornerRadius = 14
		playingButton.setImage(.init(systemName: "play"), for: .normal)
		playingButton.addTarget(self, action: #selector(playOrPauseButtonDidTap), for: .touchUpInside)
	}
	
	// MARK: - Configure
	private func configure() {
		backgroundImageView.image = .init(named: Constants.backgroundImageName)
	}
	
	// MARK: - Actions
	@objc
	private func playOrPauseButtonDidTap() {
		if let player = player, player.isPlaying {
			playingButton.setImage(.init(systemName: "play"), for: .normal)
			player.stop()
		} else {
			playingButton.setImage(.init(systemName: "stop"), for: .normal)
			let urlString = Bundle.main.path(
				forResource: viewModel.sourseInfo?.name, ofType: viewModel.sourseInfo?.type
			)
			
			do {
				try AVAudioSession.sharedInstance().setMode(.default)
				try AVAudioSession.sharedInstance().setActive(true, options: .notifyOthersOnDeactivation)
				
				guard let urlString = urlString else { return }
				
				player = try AVAudioPlayer(contentsOf: URL(fileURLWithPath: urlString))
				
				guard let player = player else { return }
				player.play()
			}
			catch {
				print(error)
			}
		}
	}
}
