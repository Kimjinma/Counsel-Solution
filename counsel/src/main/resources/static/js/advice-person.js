function handleSurveySubmit() {
    const surveyData = {
        question1: document.querySelector('input[name="option1"]:checked')?.value,
        question2: document.querySelector('input[name="option2"]:checked')?.value,
        question3: document.querySelector('input[name="option3"]:checked')?.value,
        schedule: document.querySelector('input[name="time"]:checked')?.value,
    };

    if (!surveyData.question1 || !surveyData.schedule) {
        alert('모든 항목을 선택해주세요.');
        return;
    }

    fetch('/api/advice/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(surveyData),
    })
        .then((response) => {
            if (response.ok) {
                showAlert();
            } else {
                alert('신청에 실패했습니다. 다시 시도해주세요.');
            }
        })
        .catch((error) => console.error('Error:', error));
}

function showAlert() {
    document.getElementById('alertMessage').style.display = 'block';
}

function hideAlert() {
    document.getElementById('alertMessage').style.display = 'none';
}
