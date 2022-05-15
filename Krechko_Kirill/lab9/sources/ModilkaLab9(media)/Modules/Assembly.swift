//
//  Assembly.swift
//  Lesson26
//
//  Created by Harbros 109 on 8.01.22.
//

import UIKit
import AVKit
import AVFoundation

// MARK: - ModulesAssembly
class ModulesAssembly {

	// MARK: - Public methods
	
	func makeMain(output: MainOutput) -> UIViewController {
		let viewModel = MainViewModel(usecase: getInfoUsecase(), output: output)
		let view = MainViewController(viewModel: viewModel)
		return view
	}
	
	func makeVideoPlayer(sourseInfo: SourseInfo) -> UIViewController {
		let player = AVPlayer(url: URL(fileURLWithPath: Bundle.main.path(forResource: sourseInfo.name, ofType: sourseInfo.type) ?? .init()))
		let vc = AVPlayerViewController()
		vc.player = player
		return vc
	}
	
	func makeCreatingPhoto() -> UIViewController {
		let viewModel = CreatingPhotoViewModel()
		let view = CreatingPhotoViewController(viewModel: viewModel)
		return view
	}
	
	func makePlayAudio() -> UIViewController {
		let viewModel = PlayAudioViewModel(usecase: getInfoUsecase())
		let view = PlayAudioViewController(viewModel: viewModel)
		return view
	}
	
	// MARK: -  Private methods
	private func getInfoUsecase() -> InfoUsecase {
		return InfoUsecaseImpl()
	}
}
