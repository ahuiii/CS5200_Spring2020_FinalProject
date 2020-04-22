import React, { Component } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { connect } from "react-redux";
import { getProjectByName } from "../../actions/projectActions";

class SearchProject extends Component {
  constructor() {
    super();
    this.onSubmit = this.onSubmit.bind(this);
  }


  state = {
    ObjectSearch: null,
  };

  onSubmit(e) {
    e.preventDefault();
    this.props.getProjectByName(this.state.ObjectSearch)
  }

  render() {
    const { ObjectSearch } = this.state;

    return (
      <div>
        <Form onSubmit={this.onSubmit}>
          <Form.Group controlId="formBasicEmail">
            <Form.Control
              type="projectName"
              placeholder="Enter project name"
              name="nameSearch"
              value={ObjectSearch || ""}
              onChange={async (e) =>
                this.setState({ ObjectSearch: e.target.value })
              }
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            Search Project By Name
          </Button>
        </Form>
      </div>
    );
  }
}


const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getProjectByName }
)(SearchProject);
