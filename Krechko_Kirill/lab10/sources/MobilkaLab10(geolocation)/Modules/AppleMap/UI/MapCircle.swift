//
//  MapCircle.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import Foundation
import MapKit

private enum Constants {
    static let circleWidth: Double = 3
    static let circleColor = UIColor.purple
}

class MapCircle: MKCircle {

    override init() {
        super.init()
    }

    func rendererCircle() -> MKOverlayRenderer {
        let circle = MKCircleRenderer(overlay: self)
        circle.strokeColor = Constants.circleColor
        circle.lineWidth = Constants.circleWidth
        return circle
    }
}
