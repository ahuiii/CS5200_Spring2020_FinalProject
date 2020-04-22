import React, { Component } from "react";
import ListGroup from "react-bootstrap/ListGroup";
import axios from "axios";

export default class Team extends Component {
  constructor(props) {
    super(props);
  }

  async componentDidMount() {
    await this.fetchTeams(this.props.projectID);
  }

  columns = [
    {
      Header: "ID",
      accessor: "id", // accessor is the "key" in the data
    },
    {
      Header: "Name",
      accessor: "team_name",
    },
    {
      Header: "Engineers",
      accessor: "",
    },
  ];

  state = {
    team: {},
    engineers: [],
  };

  async fetchTeams(projectID) {
    const resp = await axios.get(`/api/admin/team/${projectID}`);
    let team = resp["data"];
    if (team !== "") {
      this.setState({
        team,
        engineers: team["engineerInTeam"],
      });
    }
  }

  render() {
    let { team, engineers } = this.state;
    return (
      <div>
        <ListGroup variant="flush">
          <h6>{`Team: ${team.teamName}`}</h6>
          {engineers.map((engineer) => (
            <ListGroup.Item key={engineer.id}>
              {engineer.fullName}
            </ListGroup.Item>
          ))}
        </ListGroup>
      </div>
    );
  }
}
