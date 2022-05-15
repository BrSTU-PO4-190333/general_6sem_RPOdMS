//
//  AppleMapViewController.swift
//  Lesson24v.2
//
//  Created by Harbros 109 on 30.01.22.
//
import MapKit

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"
    static let mapViewIdentifier = "Apple map"
	static let bstuCoordinates = CLLocationCoordinate2D(latitude: 52.095812, longitude: 23.757942)
    static let region = MKCoordinateRegion(
        center: Constants.bstuCoordinates, latitudinalMeters: 15_000, longitudinalMeters: 15_000
    )
    static let buttonBottomIndent: CGFloat = -20
    static let studentInfoTopIndent: CGFloat = 40
    static let studentInfoTrailingIndent: CGFloat = -20
    static let studentInfoHeight: CGFloat = 80
    static let studentInfoWidth: CGFloat = 120
    static let routesButtonLeadingIndent: CGFloat = 20
    static let countOfCirclesRange: Range<Int> = .init(1...3)
    static let minimalCircleRadius: Double = 5_000
}

// MARK: - AppleMapViewController
class AppleMapViewController: UIViewController {

    private var viewModel: AppleMapViewModelInterface
    private let mapView = AppleMap()
    private let venue = MKPointAnnotation()
    private var circlesCenter = Constants.bstuCoordinates
    private let showStudentsButton = ShowStudentButton()
    private let studentInfoView = StudentInfoLabel()
    private let routesButton = SetupRoutesButton()

    init(viewModel: AppleMapViewModelInterface) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel.viewDidLoad()
        setupAppleMap()
        setupMapItems()
        setupShowStudentsButton()
        showStudentsButtonPressed()
        setupStudentInfoView()
        setupRoutesButton()
    }

    private func setupAppleMap() {
        view.addSubview(mapView)
        mapView.delegate = self
        mapView.frame = view.frame
    }

    private func setupMapItems() {
        viewModel.tableData.forEach { student in mapView.addAnnotation(StudentAnnotation(student: student)) }
        venue.coordinate = circlesCenter
        mapView.addAnnotation(venue)

        setupCircles()
    }

    private func setupCircles() {
        Constants.countOfCirclesRange.forEach { number in
            mapView.addOverlay(MapCircle(center: circlesCenter, radius: Constants.minimalCircleRadius * Double(number)))
        }
    }

    private func setupShowStudentsButton() {
        view.addSubview(showStudentsButton)
        showStudentsButton.addTarget(self, action: #selector(showStudentsButtonPressed), for: .touchUpInside)

        NSLayoutConstraint.activate([
            showStudentsButton.trailingAnchor.constraint(
                equalTo: mapView.trailingAnchor, constant: Constants.buttonBottomIndent
            ),
            showStudentsButton.bottomAnchor.constraint(
                equalTo: mapView.bottomAnchor, constant: Constants.buttonBottomIndent
            )
        ])
    }

    @objc
    private func showStudentsButtonPressed() {
        mapView.setRegion(Constants.region, animated: true)
    }

    private func setupStudentInfoView() {
        view.addSubview(studentInfoView)
        studentInfoView.text = viewModel.setupCountOfStudentsText(mapView: mapView, circlesCenter: circlesCenter)

        NSLayoutConstraint.activate([
            studentInfoView.topAnchor.constraint(equalTo: view.topAnchor, constant: Constants.studentInfoTopIndent),
            studentInfoView.trailingAnchor.constraint(
                equalTo: view.trailingAnchor, constant: Constants.studentInfoTrailingIndent
            ),
            studentInfoView.widthAnchor.constraint(equalToConstant: Constants.studentInfoWidth),
            studentInfoView.heightAnchor.constraint(equalToConstant: Constants.studentInfoHeight)
        ])
    }

    private func setupRoutesButton() {
        view.addSubview(routesButton)
        routesButton.addTarget(self, action: #selector(routesButtonPressed), for: .touchUpInside)

        NSLayoutConstraint.activate([
            routesButton.leadingAnchor.constraint(
                equalTo: mapView.leadingAnchor, constant: Constants.routesButtonLeadingIndent
            ),
            routesButton.bottomAnchor.constraint(equalTo: mapView.bottomAnchor, constant: Constants.buttonBottomIndent)
        ])
    }

    @objc
    private func routesButtonPressed() {
        mapView.removeOverlays(mapView.overlays)
        setupCircles()
        viewModel.setStudentsWillTheyCome(annotations: mapView.annotations, center: circlesCenter)
        createPathes()
    }

    private func createPathes() {
        mapView.annotations.forEach { annotation in
            guard let annotation = annotation as? StudentAnnotation else { return }
            if annotation.willCome == true { createPath(sourceLocation: annotation.coordinate) }
        }
    }

    private func createPath(sourceLocation: CLLocationCoordinate2D) {
        let direction = Direction(request: .init()).createDirection(
            sourceLocation: sourceLocation, venueLocation: venue.coordinate
        )
        direction.calculate { response, _ in
            guard let respons = response,
                !respons.routes.isEmpty,
                let route = respons.routes.first
            else { return }
            self.mapView.addOverlay(route.polyline, level: MKOverlayLevel.aboveRoads)
        }
    }
}

// MARK: - MKMapViewDelegate

extension AppleMapViewController: MKMapViewDelegate {
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        let annotationView = mapView.dequeueReusableAnnotationView(withIdentifier: Constants.mapViewIdentifier)

        if let annotation = annotation as? StudentAnnotation {
            guard let id = annotation.id else { return .init() }
            return StudentAnnotationView().createStudentAnnotationView(
                annotationView: annotationView, annotation: annotation, image: viewModel.getStudentImage(id: id)
            )
        }
        return VenueAnnotationView().createVenueAnnotationView(annotationView: annotationView, annotation: annotation)
    }

    func mapView(
        _ mapView: MKMapView, annotationView view: MKAnnotationView, calloutAccessoryControlTapped control: UIControl
    ) {
        guard let selectStudentId = (view.annotation as? StudentAnnotation)?.id else { return }
        viewModel.showStudentInfo(id: selectStudentId)
    }

    func mapView(_ mapView: MKMapView, rendererFor overlay: MKOverlay) -> MKOverlayRenderer {
        guard let overlay = overlay as? MapCircle else {
            return Direction(request: .init()).rendererPath(overlay: overlay)
        }
        return overlay.rendererCircle()
    }

    func mapView(
        _ mapView: MKMapView,
        annotationView view: MKAnnotationView,
        didChange newState: MKAnnotationView.DragState,
        fromOldState oldState: MKAnnotationView.DragState
    ) {
        switch newState {
        case .ending, .canceling:
            circlesCenter = view.annotation?.coordinate ?? .init()
            venue.coordinate = circlesCenter
            mapView.removeOverlays(mapView.overlays)
            setupCircles()
            createPathes()
            studentInfoView.text = viewModel.setupCountOfStudentsText(mapView: mapView, circlesCenter: circlesCenter)

        default:
            break
        }
    }
}
