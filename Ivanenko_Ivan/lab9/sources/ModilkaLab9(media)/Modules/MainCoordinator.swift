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
		
	func showVideo(sourseInfo: SourseInfo) {
		let vc = assembly.makeVideoPlayer(sourseInfo: sourseInfo)
		mainNavigationController?.present(vc, animated: true)
	}
	
	func playMusic() {
		let vc = assembly.makePlayAudio()
		mainNavigationController?.pushViewController(vc, animated: true)
	}
	
	func createPhoto() {
		let vc = assembly.makeCreatingPhoto()
		mainNavigationController?.pushViewController(vc, animated: true)
	}
	
	func openGallery() {
		let picker = UIImagePickerController()
		picker.allowsEditing = false
		picker.sourceType = UIImagePickerController.SourceType.photoLibrary
		mainNavigationController?.present(picker, animated: true)
	}
}
