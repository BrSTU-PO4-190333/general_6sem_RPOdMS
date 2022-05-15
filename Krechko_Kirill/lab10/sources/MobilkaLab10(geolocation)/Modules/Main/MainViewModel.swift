//
//  MainViewModel.swift
//  Lesson20.1
//
//  Created by Harbros 109 on 7.12.21.
//

// MARK: - MainViewModelInterface

protocol MainViewModelInterface {
    func viewDidLoad()

    func openAppleMapButtonPressed()
    func openGoogleMapButtonPressed()
}

// MARK: - MainViewModel

class MainViewModel {

    weak var view: MainViewControllerInterface?
    weak var output: MainOutput?

    init(output: MainOutput) {
        self.output = output
    }
}

// MARK: - MainViewModelInterface

extension MainViewModel: MainViewModelInterface {
    func viewDidLoad() { }

    func openAppleMapButtonPressed() {
        output?.showAppleMap()
    }

    func openGoogleMapButtonPressed() {
        output?.showGoogleMap()
    }
}
