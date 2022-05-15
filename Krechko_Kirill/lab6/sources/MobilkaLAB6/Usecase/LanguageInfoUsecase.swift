//
//  LanguageInfoUsecase.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 29.03.22.
//

// MARK: - Constants
private extension Constants {
	static let cppInfo = "C++ is an object-oriented programming language which gives a clear structure to programs and allows code to be reused, lowering development costs. C++ is portable and can be used to develop applications that can be adapted to multiple platforms. C++ is fun and easy to learn!"
	
	static let swiftInfo = "SWIFT is a messaging network that financial institutions use to securely transmit information and instructions through a standardized system of codes. Although SWIFT has become a crucial part of global financial infrastructure, it is not a financial institution itself: SWIFT does not hold or transfer assets."
	
	static let javaInfo = "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible."
}

// MARK: - LanguageInfoUsecaseImpl
protocol LanguageInfoUsecase {
	func getLanguageInfo(type: LanguageType) -> String
}

// MARK: - LanguageInfoUsecase
class LanguageInfoUsecaseImpl {
	init() { }
}

// MARK: - LanguageInfoUsecaseImpl
extension LanguageInfoUsecaseImpl: LanguageInfoUsecase {
	func getLanguageInfo(type: LanguageType) -> String {
		switch type {
			case .cpp:
				return Constants.cppInfo
			case .swift:
				return Constants.swiftInfo
			case .java:
				return Constants.javaInfo
		}
	}
}
