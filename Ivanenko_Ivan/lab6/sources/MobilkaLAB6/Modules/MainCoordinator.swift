//
//  MainCoordinator.swift
//  Lesson26
//
//  Created by Harbros 109 on 8.01.22.
//

import UIKit

// MARK: - MainCoordinator
class MainCoordinator {
	
	// MARK: - Properties
    private let assembly: ModulesAssembly
	private weak var infoInput: InfoInput?
	
	weak var mainNavigationController: UINavigationController?
	
	// MARK: - Init
    init(assembly: ModulesAssembly) {
        self.assembly = assembly
    }
	
	// MARK: - Public Methods
    func start(window: UIWindow) {
		let mainVC = assembly.makeMain(output: self)
		let nvc = UINavigationController(rootViewController: mainVC)
        window.rootViewController = nvc
        window.makeKeyAndVisible()
		mainNavigationController = nvc
    }
}

// MARK: - MainOutput
extension MainCoordinator: MainOutput {
	func languageInfoButtonDidTap(languageType: LanguageType) {
		let infoVC = assembly.makeInfo(usecase: assembly.getLanguageInfoUsecase(), languageType: languageType)
		mainNavigationController?.pushViewController(infoVC, animated: true)
	}
}
