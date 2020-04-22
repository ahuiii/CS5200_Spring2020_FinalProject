import React, { Component } from "react";
import Table from "react-bootstrap/Table";
import axios from "axios";

export default class coronavirus extends Component {
  async componentDidMount() {
    await this.getCOVID();
  }

  columns = [
    // {
    //   Header: "ID",
    //   accessor: "id", // accessor is the "key" in the data
    // },
    {
      Header: "USAState",
      accessor: "USAState",
    },
    {
      Header: "TotalCases",
      accessor: "TotalCases",
    },
    {
      Header: "NewCases",
      accessor: "NewCases", // accessor is the "key" in the data
    },
    {
      Header: "TotalDeaths",
      accessor: "TotalDeaths",
    },
    {
      Header: "NewDeaths",
      accessor: "NewDeaths",
    },
    {
      Header: "ActiveCases",
      accessor: "ActiveCases", // accessor is the "key" in the data
    },
    {
      Header: "TotalTests",
      accessor: "TotalTests",
    },
  ];

  state = {
    COVID: [],
  };

  async getCOVID() {
    const resp = await axios.get("/api/covid19/update");
    let COVID = resp["data"];
    if (COVID != "") {
      this.setState({
        COVID,
      });
    }
  }

  render() {
    let { COVID } = this.state;
    console.log(COVID);
    return (
      <div>
        <br />
        <h1> COVID Trakers</h1>
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
            {COVID.map((cases) => (
              <tr key={cases.id}>
                {/* <td>{cases.id}</td> */}
                <td>{cases.usastate}</td>
                <td>{cases.totalCases}</td>
                <td>{cases.newCases}</td>
                <td>{cases.totalDeaths}</td>
                <td>{cases.newDeaths}</td>
                <td>{cases.activeCases}</td>
                <td>{cases.totalTests}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}
