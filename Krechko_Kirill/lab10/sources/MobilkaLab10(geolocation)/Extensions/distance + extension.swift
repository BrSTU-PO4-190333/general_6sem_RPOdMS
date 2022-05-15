//
//  distance + extension.swift
//  Lesson24
//
//  Created by Harbros 109 on 4.01.22.
//

import MapKit

extension CLLocationCoordinate2D {

    func distance(from: CLLocationCoordinate2D) -> CLLocationDistance {
        let from = CLLocation(latitude: from.latitude, longitude: from.longitude)
        let to = CLLocation(latitude: self.latitude, longitude: self.longitude)
        return from.distance(from: to)
    }
}
