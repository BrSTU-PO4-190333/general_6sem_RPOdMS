//
//  Direction.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import Foundation
import MapKit

private enum Constants {
    static let pathWidth: Double = 3
    static let pathStrokeColor = UIColor.systemBlue
}

class Direction: MKDirections {

    override init(request: MKDirections.Request) {
        super.init(request: request)
    }

    func createDirection(
        sourceLocation: CLLocationCoordinate2D, venueLocation: CLLocationCoordinate2D
    ) -> MKDirections {
        let directionRequest = MKDirections.Request()
        directionRequest.source = MKMapItem(placemark: .init(coordinate: sourceLocation, addressDictionary: nil))
        directionRequest.destination = MKMapItem(placemark: .init(coordinate: venueLocation, addressDictionary: nil))

        directionRequest.requestsAlternateRoutes = true
        directionRequest.transportType = .any

        return MKDirections(request: directionRequest)
    }

    func rendererPath(overlay: MKOverlay) -> MKOverlayRenderer {
        let rendere = MKPolylineRenderer(overlay: overlay)
        rendere.lineWidth = Constants.pathWidth
        rendere.strokeColor = Constants.pathStrokeColor
        return rendere
    }
}
