//
//  LanguageType.swift
//  MobilkaLAB6
//
//  Created by Kiril Krechko on 24.03.22.
//

import UIKit

// MARK: - LanguageType
enum LanguageType {
	case cpp
	case swift
	case java
	
	var languageImageName: String {
		switch self {
			case .cpp:
				return "cppLogo"
			case .swift:
				return "swiftLogo"
			case .java:
				return "javaLogo"
		}
	}
}
