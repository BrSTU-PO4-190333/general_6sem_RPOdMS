//
//  MainIO.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

// MARK: - MainOutput
protocol MainOutput: AnyObject {
	func languageInfoButtonDidTap(languageType: LanguageType)
}
