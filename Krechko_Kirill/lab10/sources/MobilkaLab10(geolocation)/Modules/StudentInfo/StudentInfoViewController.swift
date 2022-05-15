//
//  StudentInfoViewController.swift
//  Lesson24
//
//  Created by Harbros 109 on 3.01.22.
//

import UIKit

// MARK: - Constants

private enum Constants {
    static let fatalErrorText = "init(coder:) has not been implemented"
    static let identifier = "Table View"
}

// MARK: - StudentInfoViewControllerInterface

protocol StudentInfoViewControllerInterface: AnyObject {
    func reloadData()
}

// MARK: StudentInfoViewController

class StudentInfoViewController: UIViewController {

    private var viewModel: StudentInfoViewModelInterface

    private let tableView = UITableView()
    private let image = UIImageView()

    init(viewModel: StudentInfoViewModelInterface) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError(Constants.fatalErrorText)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        viewModel.viewDidLoad()

        setupImageView()
        setupTableView()
    }

    private func setupTableView() {
        view.addSubview(tableView)
        setupTableViewConstraints()

        tableView.register(StudentInfoCell.self, forCellReuseIdentifier: Constants.identifier)
        tableView.dataSource = self
        tableView.separatorColor = .clear
    }

    private func setupImageView() {
        view.addSubview(image)
        setupImageViewConstraints()
        image.contentMode = .redraw
    }

    private func setupTableViewConstraints() {
        tableView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            tableView.topAnchor.constraint(equalTo: image.bottomAnchor),
            tableView.bottomAnchor.constraint(equalTo: view.bottomAnchor)
        ])
    }

    private func setupImageViewConstraints() {
        image.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            image.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            image.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            image.topAnchor.constraint(equalTo: view.topAnchor),
            image.heightAnchor.constraint(equalToConstant: view.bounds.height / 3)
        ])
    }
}

// MARK: - StudentViewControllerInterface

extension StudentInfoViewController: StudentInfoViewControllerInterface {
    func reloadData() {
        tableView.reloadData()
    }
}

extension StudentInfoViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.tableData.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: Constants.identifier, for: indexPath)
            as? StudentInfoCell
        else { return UITableViewCell() }

        cell.setup(with: viewModel.tableData[indexPath.row])
        cell.selectionStyle = .none

        image.image = viewModel.image
        return cell
    }
}
