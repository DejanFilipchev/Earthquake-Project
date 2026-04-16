import './App.css';
import {useEffect, useState} from "react";
import EarthquakeCard from './components/EarthquakeCard'
import axios from "axios";
import FilterBar from "./components/FilterBar";

function App() {

    const [data, setData] = useState([]);
    const [isWaiting, setIsWaiting] = useState(false);

    const loadData = (path) => {
        setIsWaiting(true);

        axios.get(`http://localhost:8080/api/earthquakes${path}`)
            .then(response => {
                setData(response.data);
                setIsWaiting(false);
            })
            .catch(error => {
                console.error(error);
                setIsWaiting(false);
            });
    };

    useEffect(() => {
        loadData('/listall');
    }, []);

    const handleSync = () => {
        setIsWaiting(true);
        axios.get('http://localhost:8080/api/earthquakes/fetch')
            .then(() => {
                loadData('/listall');
            });
    };

    const handleDelete = (id) => {
        axios.get(`http://localhost:8080/api/earthquakes/delete/${id}`)
            .then(response  => {
                setData(data.filter(item => item.id !== id));
                console.log(response.data);
            })
            .catch(error => {
                const message = error.response?.data?.message || "Something went wrong";
                console.log(message);
            });
    };

    const handleTimeChange = (event) => {
        const minutes = parseInt(event.target.value);
        if(minutes === 0) {
            loadData('/listall');
        }else{
            loadData(`/after/${Date.now() - (minutes * 60 * 1000)}`);
}
    }

    return(
        <div className="dashboard-container">
            <h1 className="dashboard-title">Earthquake Dashboard</h1>
                <FilterBar
                    onSync={handleSync}
                    onLoad={loadData}
                    onTimeChange={handleTimeChange}
                />

            {isWaiting ? null : (
                <div className="earthquake-grid">
                    {data.map(item => (
                        <EarthquakeCard
                            key={item.id}
                            item={item}
                            onDelete={handleDelete}
                        />
                    ))}
                </div>
            )}
        </div>
    );
}



export default App;
