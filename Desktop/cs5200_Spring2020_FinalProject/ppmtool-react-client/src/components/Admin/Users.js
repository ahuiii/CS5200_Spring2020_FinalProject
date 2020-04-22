import React, { Component } from "react";
import { Link } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Table from "react-bootstrap/Table";
import classnames from "classnames";
import axios from "axios";

export default class UserList extends Component {
  async componentDidMount() {
    await this.fetchUsers();
  }

  columns = [
    {
      Header: "ID",
      accessor: "id", // accessor is the "key" in the data
    },
    {
      Header: "Name",
      accessor: "fullName",
    },
    {
      Header: "Email",
      accessor: "userName",
    },
    {
      Header: "Role",
      accessor: "userRole",
    },
  ];

  state = {
    users: [],
    nameSearch: null,
    showAddUserModal: false,
    newUser: {},
  };

  async fetchUsers() {
    //  TODO: add api call to query all users and setState
    let { nameSearch } = this.state;
    const resp = await axios.get("/api/users/all", {
      headers: { token: localStorage.getItem("jwtToken") },
    });
    let users = resp["data"];
    // this.fakeUsers needs to be replaced with api call results
    if (nameSearch) {
      users = users.filter((u) => u.fullName.includes(nameSearch));
    }
    this.setState({
      users,
    });
  }

  async deleteUser(userId) {
    await axios.delete("/api/users/delete/" + userId, {
      headers: { token: localStorage.getItem("jwtToken") },
    });
    // this.fakeUsers = this.fakeUsers.filter((u) => u.id !== userId); // this needs to be deleted in real case
    this.fetchUsers();
  }

  async addUser() {
    let { newUser } = this.state;
    // TODO:  call create user api
    await axios.post("/api/users/register", newUser, {
      headers: { token: localStorage.getItem("jwtToken") },
    });
    this.setState({
      newUser: {},
      showAddUserModal: false,
    });
    this.fetchUsers();
  }

  render() {
    const { users, newUser, showAddUserModal, nameSearch } = this.state;
    return (
      <div className="container">
        <input
          type="text"
          className={classnames("form-control form-control-lg")}
          placeholder="Search Full Name"
          name="fullNameSearch"
          value={nameSearch || ""}
          onChange={async (e) => {
            this.setState(
              {
                nameSearch: e.target.value,
              },
              async () => {
                await this.fetchUsers();
              }
            );
          }}
        />
        <br />

        <Button
          onClick={() =>
            this.setState({
              showAddUserModal: true,
            })
          }
        >
          Create User
        </Button>

        <Modal
          show={showAddUserModal}
          onHide={() => {
            this.setState({
              showAddUserModal: false,
              newUser: {},
            });
          }}
        >
          <Modal.Header closeButton>
            <Modal.Title>Create User</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div className="form-group">
              <input
                type="text"
                className={classnames("form-control form-control-lg")}
                placeholder="Full Name"
                name="fullName"
                value={newUser.fullName || ""}
                onChange={(e) =>
                  this.setState({
                    newUser: {
                      ...newUser,
                      fullName: e.target.value,
                    },
                  })
                }
              />
            </div>
            <div className="form-group">
              <input
                type="text"
                className={classnames("form-control form-control-lg")}
                placeholder="Email Address (Username)"
                name="username"
                value={newUser.userName || ""}
                onChange={(e) =>
                  this.setState({
                    newUser: {
                      ...newUser,
                      userName: e.target.value,
                    },
                  })
                }
              />
            </div>
            <div className="form-group">
              <input
                type="password"
                className={classnames("form-control form-control-lg")}
                placeholder="Password"
                name="password"
                value={newUser.password || ""}
                onChange={(e) =>
                  this.setState({
                    newUser: {
                      ...newUser,
                      password: e.target.value,
                    },
                  })
                }
              />
            </div>
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="secondary"
              onClick={() => {
                this.setState({
                  showAddUserModal: false,
                  newUser: {},
                });
              }}
            >
              Cancel
            </Button>
            <Button variant="primary" onClick={() => this.addUser()}>
              Save
            </Button>
          </Modal.Footer>
        </Modal>

        <Table>
          <thead>
            <tr>
              {this.columns.map((column) => (
                <th key={column.Header}>{column.Header}</th>
              ))}
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {users.map((user) => (
              <tr key={user.id}>
                <td>
                  <Link to={"/user/" + user.id}>{user.id}</Link>
                </td>
                <td>{user.fullName}</td>
                <td>{user.userName}</td>
                <td>{user.userRole}</td>
                <td>
                  <Button
                    variant="outline-primary"
                    onClick={() => this.deleteUser(user.id)}
                  >
                    Delete
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}
