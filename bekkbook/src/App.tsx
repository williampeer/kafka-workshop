import React, {Component} from 'react';
import {hot} from 'react-hot-loader';
import './App.css';
import Feed from "./Components/Feed";
import {QueryClient, QueryClientProvider} from "react-query";

const queryClient = new QueryClient();

class App extends Component {
    render() {
        return (
            <QueryClientProvider client={queryClient}>
                <div className="App">
                    {/*<h1>Bekkbok blir fosskok™️</h1>*/}
                    <h1 style={{padding: "1rem", borderBottom: "solid"}}>
                        bekkbook™️
                    </h1>
                    <Feed/>
                </div>
            </QueryClientProvider>
        );
    }
}

export default hot(module)(App);
