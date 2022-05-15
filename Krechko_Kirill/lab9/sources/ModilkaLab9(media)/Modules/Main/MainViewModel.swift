//
//  MainViewModel.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import Foundation

// MARK: - MainViewModelInterface
protocol MainViewModelInterface {
	func showVideo()
	func playMusic()
	func createPhoto()
	func openGallery()
}

// MARK: - MainViewModel
class MainViewModel {
	
	// MARK: - Properties
	private let usecase: InfoUsecase
	weak var output: MainOutput?
	
	// MARK: - Init
	init(usecase: InfoUsecase, output: MainOutput) {
		self.usecase = usecase
		self.output = output
	}
}

// MARK: - MainViewModelInterface
extension MainViewModel: MainViewModelInterface {

	func showVideo() {
		let videoInfo = usecase.getInfo(type: .video)
		output?.showVideo(sourseInfo: videoInfo)
	}
	
	func playMusic() {
		output?.playMusic()
	}
	
	func createPhoto() {
		output?.createPhoto()
	}
	
	func openGallery() {
		output?.openGallery()
	}	
}
