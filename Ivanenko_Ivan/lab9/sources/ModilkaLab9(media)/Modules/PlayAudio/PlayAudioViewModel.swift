//
//  PlayAudioViewModel.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

// MARK: - PlayAudioViewModelInterface
protocol PlayAudioViewModelInterface {
	var sourseInfo: SourseInfo? { get }
	
	func viewDidLoad()
}

// MARK: - PlayAudioViewModel
class PlayAudioViewModel {
	
	// MARK: - Properties
	private let usecase: InfoUsecase
	var sourseInfo: SourseInfo?
	
	// MARK: - Init
	init(usecase: InfoUsecase) {
		self.usecase = usecase
	}
}

// MARK: - MainViewModelInterface
extension PlayAudioViewModel: PlayAudioViewModelInterface {
	func viewDidLoad() {
		sourseInfo = usecase.getInfo(type: .track)
	}
}
