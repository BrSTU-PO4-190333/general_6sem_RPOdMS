//
//  InfoViewModel.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import UIKit

// MARK: - InfoViewModelInterface
protocol InfoViewModelInterface {
	var languageImageName: String { get }
	var languageInfo: String? { get }
	var backgroundImageName: String { get }
 
	func viewDidLoad()
}

// MARK: - InfoViewModel
class InfoViewModel {
	// MARK: - Properties
	private let usecase: LanguageInfoUsecase
	private let languageType: LanguageType
	
	var languageImageName: String
	var languageInfo: String?
	var backgroundImageName = "sea_background"
	
	// MARK: - Init
	init(usecase: LanguageInfoUsecase, languageType: LanguageType) {
		self.usecase = usecase
		self.languageType = languageType
		languageImageName = languageType.languageImageName
	}
}

// MARK: - InfoViewModelInterface
extension InfoViewModel: InfoViewModelInterface {
	func viewDidLoad() {
		getLanguageInfo()
	}
	
	private func getLanguageInfo() {
		languageInfo = usecase.getLanguageInfo(type: languageType)
	}
}

// MARK: - InfoInput
extension InfoViewModel: InfoInput { }

