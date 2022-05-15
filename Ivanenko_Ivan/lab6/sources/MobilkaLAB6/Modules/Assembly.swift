//
//  Assembly.swift
//  Lesson26
//
//  Created by Harbros 109 on 8.01.22.
//

import UIKit

// MARK: - ModulesAssembly
class ModulesAssembly {
	
	// MARK: - Public methods
	
	func getLanguageInfoUsecase() -> LanguageInfoUsecase {
		return LanguageInfoUsecaseImpl()
	}
	
	func makeMain(output: MainOutput) -> UIViewController {
		let viewModel = MainViewModel(output: output)
		let view = MainViewController(viewModel: viewModel)
		viewModel.output = output
		return view
	}
	
	func makeInfo(usecase: LanguageInfoUsecase, languageType: LanguageType) -> UIViewController {
		let viewModel = InfoViewModel(usecase: usecase, languageType: languageType)
		let view = InfoViewController(viewModel: viewModel)
		return view
	}
}
