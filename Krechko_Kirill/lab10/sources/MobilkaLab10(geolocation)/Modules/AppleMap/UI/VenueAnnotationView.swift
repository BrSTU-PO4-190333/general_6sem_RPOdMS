//
//  VenueAnnotationView.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import Foundation
import MapKit

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"
    static let venueSize = CGSize(width: 40, height: 50)
    static let venueImageName = "venue"
    static let mapViewIdentifier = "Apple map"
}

class VenueAnnotationView: MKAnnotationView {

    override init(annotation: MKAnnotation?, reuseIdentifier: String?) {
        super.init(annotation: annotation, reuseIdentifier: reuseIdentifier)
        setupUI()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    private func setupUI() {
        self.image = UIImage(named: Constants.venueImageName)?.resizeImageTo(size: Constants.venueSize)
        self.isDraggable = true
    }

    func createVenueAnnotationView(annotationView: MKAnnotationView?, annotation: MKAnnotation) -> VenueAnnotationView {

        if annotationView == nil {
            return VenueAnnotationView(annotation: annotation, reuseIdentifier: Constants.mapViewIdentifier)
        } else {
            annotationView?.annotation = annotation
            return annotationView as? VenueAnnotationView ?? .init()
        }
    }
}
