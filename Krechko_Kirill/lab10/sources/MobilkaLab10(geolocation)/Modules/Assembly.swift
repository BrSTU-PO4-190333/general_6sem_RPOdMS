//
//  Assembly.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import UIKit

class ModulesAssembly {

    func getStudentsUsecase() -> StudentsUsecase {
        StudentsUsecaseImpl()
    }

    func makeMain(output: MainOutput) -> UIViewController {
        let viewModel = MainViewModel(output: output)
        let view = MainViewController(viewModel: viewModel)
        viewModel.view = view
        viewModel.output = output
        return view
    }

    func makeAppleMap(output: AppleMapOutput, usecase: StudentsUsecase) -> UIViewController {
        let viewModel = AppleMapViewModel(output: output, usecase: usecase)
        let view = AppleMapViewController(viewModel: viewModel)
        viewModel.output = output
        return view
    }

    func makeStudentInfo(output: StudentInfoOutput, usecase: StudentsUsecase)
    -> (view: UIViewController, input: StudentInfoInput) {
        let viewModel = StudentInfoViewModel(output: output, usecase: usecase)
        let view = StudentInfoViewController(viewModel: viewModel)
        viewModel.view = view
        viewModel.output = output
        return (view: view, input: viewModel)
    }
}
