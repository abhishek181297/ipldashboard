import { React, useEffect, useState } from "react"
import {MatchDetailsCard} from "../components/MatchDetailsCard";
import {MatchSmallCard} from "../components/MatchSmallCard";

export const TeamPage = () => {

    const [team, setTeam] = useState({});

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch("http://localhost:8001/team/name/Rajasthan Royals");
                const data = await response.json();
                console.log(data);
                setTeam(data);
            }
            fetchMatches();
        },[]
    );

    return (
        <div className="TeamPage">
           <h1>{team.teamName}</h1>
            <MatchDetailsCard />
            <MatchSmallCard />
            <MatchSmallCard />
            <MatchSmallCard />

        </div>
    );
}

