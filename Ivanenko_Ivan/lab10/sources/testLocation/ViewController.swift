//
//  ViewController.swift
//  testLocation
//

import UIKit
import MapKit
import CoreLocation
import SnapKit

// MARK: - ViewController
class ViewController: UIViewController {
	private let coord = CLLocationCoordinate2D(latitude: 52.095812, longitude: 23.757942)
	
	// MARK: - Properties
	private let manager = CLLocationManager()
	private lazy var coordinatesLabel: UILabel = {
		let label = UILabel()
		label.backgroundColor = .purple
		label.textAlignment = .center
		label.numberOfLines = .zero
		return label
	}()
	
	// MARK: - Life cycle
	override func viewDidLoad() {
		super.viewDidLoad()
		view.backgroundColor = .systemMint
		setupCoordinatesLabel()
	}
	
	override func viewDidAppear(_ animated: Bool) {
		super.viewDidAppear(animated)
		setupManager()
	}
	
	// MARK: - Private methods
	private func setupManager() {
		manager.requestAlwaysAuthorization()
		manager.requestWhenInUseAuthorization()
		
		if CLLocationManager.locationServicesEnabled() {
			manager.delegate = self
			manager.desiredAccuracy = kCLLocationAccuracyBest
			manager.startUpdatingLocation()
		}
	}
	
	private func setupCoordinatesLabel() {
		view.addSubview(coordinatesLabel)
		coordinatesLabel.snp.makeConstraints { make in
			make.trailing.leading.equalToSuperview().inset(25)
			make.centerY.equalToSuperview()
			make.height.equalTo(50)
		}
		coordinatesLabel.text = "Ваши координаты: \(coord)"
	}
}

// MARK: - CLLocationManagerDelegate
extension ViewController: CLLocationManagerDelegate {
	func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
		if let location = locations.first {
			var coord = location.coordinate
			coord = .init()
		}
	}
}
