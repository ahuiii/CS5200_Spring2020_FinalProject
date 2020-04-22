import React, { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import axios from "axios";
import classnames from "classnames";

export default function UserDetail(props) {
  const { register, handleSubmit } = useForm();
  const [user, setUser] = useState({});
  const [redirect, setRedirect] = useState(false);
  const [errors, setErrors] = useState({});

  function getUser(userId) {
    axios.get("/api/users/" + userId, { headers: { "token": localStorage.getItem("jwtToken") } })
      .then(function (response) {
        setUser(response["data"]);
      })
      .catch(function (error) {
        setRedirect(true);
      });
  };

  function updateUser(user) {
    axios.put("/api/users/update", user, { headers: { "token": localStorage.getItem("jwtToken") } })
      .then(function (response) {
        setUser(response["data"]);
      })
      .catch(function (error) {
        setRedirect(true);
      });
  }

  useEffect(() => {
    getUser(props.userId);
  }, []);

  function onSubmit(props) {
    if (localStorage.getItem("userRole") === "admin") {
      updateUser(Object.assign({}, user, props));
    } else {
      updateUser(Object.assign({}, user, props, { userDtype: user["userRole"] }));
    }
  }

  if (redirect) {
    localStorage.clear();
    window.location.href = "/login";
  } else {
    if (localStorage.getItem("userRole") === "admin") {
      return (
        < div className="userDetail" >
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h1 className="display-4 text-center">User Profile</h1>
                <form onSubmit={handleSubmit(onSubmit)}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.fullName
                      })}
                      placeholder="Full Name"
                      name="fullName"
                      defaultValue={user["fullName"]}
                      ref={register}
                    />
                    {errors.fullName && (
                      <div className="invalid-feedback">{errors.fullName}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.username
                      })}
                      placeholder="Email Address (Username)"
                      name="username"
                      defaultValue={user["userName"]}
                      ref={register}
                    />
                    {errors.username && (
                      <div className="invalid-feedback">{errors.username}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.password
                      })}
                      placeholder="Password"
                      name="password"
                      ref={register}
                    />
                    {errors.password && (
                      <div className="invalid-feedback">{errors.password}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.confirmPassword
                      })}
                      placeholder="Confirm Password"
                      name="confirmPassword"
                      ref={register}
                    />
                    {errors.confirmPassword && (
                      <div className="invalid-feedback">
                        {errors.confirmPassword}
                      </div>
                    )}
                  </div>
                  < div className="form-group" >
                    <label htmlFor="userRole">User Role</label>
                    <select className="form-control" id="userRole" name="userDtype" ref={register}>
                      <option value="admin">admin</option>
                      <option value="user">user</option>
                    </select>
                  </div >
                  <input type="submit" className="btn btn-info btn-block mt-4" />
                </form>
              </div>
            </div>
          </div>
        </div >
      );
    } else {
      return (
        < div className="userDetail" >
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h1 className="display-4 text-center">User Profile</h1>
                <form onSubmit={handleSubmit(onSubmit)}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.fullName
                      })}
                      placeholder="Full Name"
                      name="fullName"
                      defaultValue={user["fullName"]}
                      ref={register}
                    />
                    {errors.fullName && (
                      <div className="invalid-feedback">{errors.fullName}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.username
                      })}
                      placeholder="Email Address (Username)"
                      name="username"
                      defaultValue={user["userName"]}
                      ref={register}
                    />
                    {errors.username && (
                      <div className="invalid-feedback">{errors.username}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.password
                      })}
                      placeholder="Password"
                      name="password"
                      ref={register}
                    />
                    {errors.password && (
                      <div className="invalid-feedback">{errors.password}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.confirmPassword
                      })}
                      placeholder="Confirm Password"
                      name="confirmPassword"
                      ref={register}
                    />
                    {errors.confirmPassword && (
                      <div className="invalid-feedback">
                        {errors.confirmPassword}
                      </div>
                    )}
                  </div>
                  <input type="submit" className="btn btn-info btn-block mt-4" />
                </form>
              </div>
            </div>
          </div>
        </div >
      );
    }
  }
};