//
//  InfoUsecase.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 29.03.22.
//

// MARK: - Constants
private extension Constants {
}

// MARK: - InfoUsecase
protocol InfoUsecase {
	func getInfo(type: InfoType) -> SourseInfo
}

// MARK: - InfoUsecaseImpl
class InfoUsecaseImpl {
	init() { }
}

// MARK: - InfoUsecase
extension InfoUsecaseImpl: InfoUsecase {
	func getInfo(type: InfoType) -> SourseInfo {
		switch type {
			case .video:
				return .init(name: "train", type: "m4v")
			case .track:
				return .init(name: "audio", type: "mp3")
		}
	}
}
