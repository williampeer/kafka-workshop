import React, {Component} from 'react';
import {hot} from 'react-hot-loader';
import './App.css';
import Feed from "./Components/Feed";

class App extends Component {
  render() {
    return (
      <div className="App">
          {/*<h1>Bekkbok blir fosskok™️</h1>*/}
          <h1 style={{ padding: "1rem", borderBottom: "solid" }}>
              bekkbook™️
          </h1>
          <Feed />
      </div>
    );
  }
}

export default hot(module)(App);
