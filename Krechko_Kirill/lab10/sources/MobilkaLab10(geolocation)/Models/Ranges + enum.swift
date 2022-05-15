//
//  Ranges + enum.swift
//  Lesson24
//
//  Created by Harbros 109 on 21.01.22.
//

import MapKit

private enum Constants {
    static let highProbability = 90
    static let mediumProbability = 50
    static let lowProbability = 10

    static let lowRange: ClosedRange<CLLocationDistance> = 0...5_000
    static let mediumRange: ClosedRange<CLLocationDistance> = 5_000...10_000
    static let highRange: ClosedRange<CLLocationDistance> = 10_000...15_000
}

enum Ranges {
    case lowRange
    case mediumRange
    case highRange

    var probability: Int {
        switch self {
        case .lowRange:
            return 90

        case .mediumRange:
            return 50

        case .highRange:
            return 10
        }
    }

    var willStudentCome: Bool { probability > Int.random(in: (0...100)) }

    func getRange(studentCoordinate: CLLocationDistance) -> Ranges? {
        switch studentCoordinate {
        case 0...5_000:
            return .lowRange

        case 5_000...10_000:
            return .mediumRange

        case 10_000...15_000:
            return .highRange

        default:
            break
        }
        return nil
    }
}
