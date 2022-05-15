//
//  StudentAnnotationView.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//

import MapKit

// MARK: - Constants

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"
    static let mapViewIdentifier = "Apple map"
    static let userImageSize = CGSize(width: 40, height: 40)
}

class StudentAnnotationView: MKAnnotationView {

    override init(annotation: MKAnnotation?, reuseIdentifier: String?) {
        super.init(annotation: annotation, reuseIdentifier: reuseIdentifier)
        setupUI()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    private func setupUI() {
        self.canShowCallout = true
        self.rightCalloutAccessoryView = UIButton(type: .detailDisclosure)
    }

    func createStudentAnnotationView(
        annotationView: MKAnnotationView?, annotation: StudentAnnotation, image: UIImage
    ) -> MKAnnotationView {
        var newAnnotationView = annotationView

        if newAnnotationView == nil {
            newAnnotationView = StudentAnnotationView(
                annotation: annotation, reuseIdentifier: Constants.mapViewIdentifier
            )
        } else { newAnnotationView?.annotation = annotation }

        newAnnotationView?.image = image.resizeImageTo(size: Constants.userImageSize)
        return newAnnotationView ?? .init()
    }
}
