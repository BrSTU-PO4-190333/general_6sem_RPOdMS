//
//  MainViewModel.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import Foundation

// MARK: - MainViewModelInterface
protocol MainViewModelInterface {
	var titleLabelText: String { get }
	var backgroundImageName: String { get }
	
	func cppInfoButtonDidTaped()
	func swiftInfoButtonDidTaped()
	func javaInfoButtonDidTaped()
}

// MARK: - MainViewModel
class MainViewModel {
	
	// MARK: - Properties
	weak var output: MainOutput?
	
	var titleLabelText = "Выберите язык, про который хотите почитать"
	var backgroundImageName = "forest_background"
	
	// MARK: - Init
	init(output: MainOutput) {
		self.output = output
	}
}

// MARK: - MainViewModelInterface
extension MainViewModel: MainViewModelInterface {
	func cppInfoButtonDidTaped() {
		output?.languageInfoButtonDidTap(languageType: .cpp)
	}
	
	func swiftInfoButtonDidTaped() {
		output?.languageInfoButtonDidTap(languageType: .swift)
	}
	
	func javaInfoButtonDidTaped() {
		output?.languageInfoButtonDidTap(languageType: .java)
	}
}
