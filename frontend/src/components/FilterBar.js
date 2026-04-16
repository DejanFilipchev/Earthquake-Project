function FilterBar({onSync, onLoad, onTimeChange}) {
    return (
        <div className="filter-container">
            <button className="btn btn-gray" onClick={onSync}>Refresh Data</button>
            <button className="btn btn-gray" onClick={() => onLoad('/listall')}>List All</button>
            <button className="btn btn-gray" onClick={() => onLoad('/specificmag')}>All with magnitude greater than
                2.0
            </button>
            <select className="btn btn-gray time-picker" onChange={onTimeChange}>
                <option value="0">Filter by Time</option>
                <option value="15">Last 15 Minutes</option>
                <option value="30">Last 30 Minutes</option>
                <option value="45">Last 45 Minutes</option>
                <option value="60">Last 60 Minutes</option>
            </select>
        </div>
    )
}

export default FilterBar;