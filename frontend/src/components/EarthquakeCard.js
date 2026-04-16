function EarthquakeCard({ item, onDelete}){
    return (
        <div className="earthquake-card">
            <h4 style={{margin: '0 0 10px 0'}}>Title: {item.title}</h4>
            <p>Magnitude: <strong style={{fontSize: '19px'}}>{item.mag}</strong></p>
            <p>Magnitude Type: {item.magType}</p>
            <p>Place: {item.place}</p>
            <p>Time: {new Date(item.time).toLocaleTimeString('en-GB')}</p>
            <hr/>

            <div className="card-footer">
                <span>ID: <strong>{item.id}</strong></span>
                <button onClick={() => onDelete(item.id)} className="btn btn-delete">
                    Delete
                </button>
            </div>
        </div>
    );
}

export default EarthquakeCard;