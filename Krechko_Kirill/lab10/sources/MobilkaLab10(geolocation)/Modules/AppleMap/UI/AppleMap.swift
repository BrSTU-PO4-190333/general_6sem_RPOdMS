//
//  AppleMap.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import MapKit

// MARK: - Constants

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"
}

// MARK: - Map

class AppleMap: MKMapView {

    override init(frame: CGRect) {
        super.init(frame: frame)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }
}
