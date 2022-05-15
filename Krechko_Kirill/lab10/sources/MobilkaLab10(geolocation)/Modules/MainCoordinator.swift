//
//  MainCoordinator.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import Foundation
import UIKit

class MainCoordinator {

    private let assembly: ModulesAssembly

    weak var mainNavigationController: UINavigationController?

    private weak var studentInfoInput: StudentInfoInput?

    init(assembly: ModulesAssembly) {
        self.assembly = assembly
    }

    func start(window: UIWindow) {
        let main = assembly.makeMain(output: self)
        let nvc = UINavigationController(rootViewController: main)
        window.rootViewController = nvc
        window.makeKeyAndVisible()
        mainNavigationController = nvc
    }
}

// MARK: - MainOutput

extension MainCoordinator: MainOutput {
    func showAppleMap() {
        let appleMapVC = assembly.makeAppleMap(output: self, usecase: assembly.getStudentsUsecase())
        mainNavigationController?.pushViewController(appleMapVC, animated: true)
    }

    func showGoogleMap() {
    }
}

// MARK: - AppleMapOutput

extension MainCoordinator: AppleMapOutput {
    func showStudentInfo(student: Student) {
        let studentInfoVC = assembly.makeStudentInfo(output: self, usecase: assembly.getStudentsUsecase())

        mainNavigationController?.present(studentInfoVC.view, animated: true, completion: nil)

        studentInfoInput = studentInfoVC.input
        studentInfoInput?.showStudentInfo(student: student)
    }
}

// MARK: - StudentInfoOutput

extension MainCoordinator: StudentInfoOutput { }
